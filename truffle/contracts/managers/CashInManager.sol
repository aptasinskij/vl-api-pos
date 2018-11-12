pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "./api/ACashInManager.sol";
import "../platform/Component.sol";
import "../oracles/api/ACashInOracle.sol";
import "../storages/api/ACashInStorage.sol";
import "../controllers/api/ACashInController.sol";
import "./api/ATokenManager.sol";

contract CashInManager is ACashInManager, Named("cash-in-manager"), Mortal, Component {

    string constant CONTROLLER = "cash-in-controller";
    string constant STORAGE = "cash-in-storage";
    string constant ORACLE = "cash-in-oracle";

    string constant TOKEN_MANAGER = "token-manager";

    modifier channelInState(uint256 _cashInId, CashInLib.Status _status) {
        require(
            ACashInStorage(context.get(STORAGE)).retrieveCashInStatus(_cashInId) == _status,
            "illegal cash-in state modification"
        );
        _;
    }

    modifier onlyBy(string _name) {
        require(context.get(_name) == msg.sender, "access not allowed");
        _;
    }

    constructor(address _config) Component(_config) public {}

    function openCashInChannel(
        address _application,
        uint256 _sessionId,
        uint256 _maxBalance,
        function(uint256) external _fail,
        function(uint256, uint256) external _success,
        function(uint256, uint256, uint256) external _update
    )
        public
        onlyBy(CONTROLLER)
    {
        //TODO:implementation: check if msg.sender is registered application
        //TODO:implementation: check if session can hold open cash-in channel
        //TODO:implementation SET SESSION HAS ACTIVE CASH-IN CHANNEL FLAG
        ACashInStorage cashInStorage = ACashInStorage(context.get(STORAGE));
        uint256 id = cashInStorage.createCashIn(_sessionId, _application);
        cashInStorage.createOpen(id, _sessionId, _maxBalance, _fail, _success, _update);
        ACashInOracle(context.get(ORACLE)).onNextOpenCashIn(id, _sessionId, _maxBalance);
    }

    function confirmOpen(
        uint256 _commandId
    )
        public
        channelInState(_commandId, CashInLib.Status.CREATING)
        onlyBy(ORACLE)
    {

        ACashInStorage cashInStorage = ACashInStorage(context.get(STORAGE));
        (uint256 sessionId, uint256 maxBalance,,function(uint256, uint256) external success,) = cashInStorage.retrieveOpen(_commandId);
        //TODO:discuss WHERE AND WHEN RETRIEVE FEE PERCENT?
        //TODO:implementation: refactor session-storage to set up hasActiveCashIn flag
        cashInStorage.createAccount(_commandId, maxBalance, 100);
        cashInStorage.setCashInStatus(_commandId, CashInLib.Status.ACTIVE);
        ACashInController(context.get(CONTROLLER)).respondOpened(sessionId, _commandId, success);
    }

    function updateCashInBalance(
        uint256 _channelId,
        uint256 _amount
    )
        public
        channelInState(_channelId, CashInLib.Status.ACTIVE)
        onlyBy(ORACLE)
    {
        ACashInStorage cashInStorage = ACashInStorage(context.get(STORAGE));
        (uint256 sessionId,,) = cashInStorage.retrieveCashIn(_channelId);
        (uint256 balance, uint256 maxBalance,) = cashInStorage.retrieveAccount(_channelId);
        require(balance + _amount <= maxBalance, "cash-in balance overflow");
        cashInStorage.setAccountBalance(_channelId, balance + _amount);
        (,,,,function(uint256, uint256, uint256) external update) = cashInStorage.retrieveOpen(_channelId);
        ACashInController(context.get(CONTROLLER)).respondUpdate(sessionId, _channelId, _amount, update);
    }

    function closeCashInChannel(
        address _application,
        uint256 _sessionId,
        uint256 _channelId,
        uint256[] _fees,
        address[] _parties,
        function(uint256, uint256) external _success,
        function(uint256, uint256) external _fail
    )
        public
        channelInState(_channelId, CashInLib.Status.ACTIVE)
        onlyBy(CONTROLLER)
    {
        //TODO:implementation: check if msg.sender is registered application
        _validateCanBeClosed(_application, _sessionId, _channelId);
        _validateSplits(_channelId, _fees);
        ACashInStorage cashInStorage = ACashInStorage(context.get(STORAGE));
        cashInStorage.createSplit(_channelId, _fees, _parties);
        cashInStorage.createClose(_channelId, _sessionId, _success, _fail);
        cashInStorage.setCashInStatus(_channelId, CashInLib.Status.CLOSE_REQUESTED);
        ACashInOracle(context.get(ORACLE)).onNextCloseCashIn(_channelId, _sessionId);
    }

    function _validateCanBeClosed(address _application, uint256 _sessionId, uint256 _cashInId) private view {
        ACashInStorage cashInStorage = ACashInStorage(context.get(STORAGE));
        require(cashInStorage.retrieveCashInSessionId(_cashInId) == _sessionId, "session differs");
        require(cashInStorage.retrieveCashInApplication(_cashInId) == _application, "illegal access");
    }

    function _validateSplits(uint256 _cashInId, uint256[] _fees) private view {
        ACashInStorage cashInStorage = ACashInStorage(context.get(STORAGE));
        (uint256 balance,,uint256 fee) = cashInStorage.retrieveAccount(_cashInId);
        uint256 vaultLogicFee = (balance * fee) / 10000;
        uint256 feesAmount;
        for (uint256 i = 0; i < _fees.length; i++) feesAmount = feesAmount + _fees[i];
        require((feesAmount + vaultLogicFee) <= balance, "cash-in balance overflow");
    }

    function balanceOf(
        address _application,
        uint256 _channelId
    )
        public
        view
        returns (
            uint256 _balance
        )
    {
        ACashInStorage cashInStorage = ACashInStorage(context.get(STORAGE));
        (,address application,) = cashInStorage.retrieveCashIn(_channelId);
        require(_application == application, "illega access");
        (_balance,,) = ACashInStorage(context.get(STORAGE)).retrieveAccount(_channelId);
    }

    function confirmClose(
        uint256 _commandId
    )
        public
        channelInState(_commandId, CashInLib.Status.CLOSE_REQUESTED)
        onlyBy(ORACLE)
    {
        ACashInStorage cashInStorage = ACashInStorage(context.get(STORAGE));
        (uint256 sessionId,function(uint256, uint256) external success,) = cashInStorage.retrieveClose(_commandId);
        ATokenManager tokenManager = ATokenManager(context.get(TOKEN_MANAGER));
        tokenManager.performCashInTransfer(_commandId);
        //TODO:implementation SET SESSION HAS NO ACTIVE CASH-IN CHANNEL FLAG
        cashInStorage.setCashInStatus(_commandId, CashInLib.Status.CLOSED);
        ACashInController(context.get(CONTROLLER)).respondClosed(sessionId, _commandId, success);
    }

}