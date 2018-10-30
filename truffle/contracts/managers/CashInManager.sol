pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../libs/Libraries.sol";
import "../platform/Mortal.sol";
import "./api/ATokenManager.sol";
import "../storages/Storages.sol";
import "./api/ACashInManager.sol";
import "../platform/Component.sol";
import "./api/ASessionManager.sol";
import "./api/AParameterManager.sol";
import "../oracles/api/ACashInOracle.sol";
import "../controllers/api/ACashInController.sol";

contract CashInManager is ACashInManager, Named("cash-channels-manager"), Mortal, Component {

    using SafeMath for uint256;
    using CashInLib for address;

    string constant CASH_IN_STORAGE = "cash-in-storage";
    string constant SESSION_STORAGE = "session-storage";
    string constant APPLICATION_STORAGE = "application-storage";
    string constant PARAMETER_MANAGER = "parameter-manager";
    string constant SESSION_MANAGER = "session-manager";
    string constant TOKEN_MANAGER = "token-manager";
    string constant ORACLE = "cash-in-oracle";

    string constant CONTROLLER = "cash-in-controller";

    constructor(address _config) Component(_config) public {}

    function openCashInChannel(
        address _application,
        uint256 _sessionId,
        uint256 _maxBalance,
        function(uint256, uint256) external _success,
        function(uint256, uint256, uint256) external _update,
        function(uint256) external _fail
    )
        public
        returns (bool _accepted)
    {
        require(
            ASessionManager(context.get(SESSION_MANAGER)).validateCanOpenCashIn(_sessionId, _application),
            "cash in can not be opened"
        );
        uint256 cashInId = database.createCashIn(_sessionId, _application);
        database.createCashInOpen(cashInId, _sessionId, _maxBalance, _success, _update, _fail);
        _accepted = ACashInOracle(context.get(ORACLE)).onNextOpenCashIn(cashInId);
    }

    function confirmOpen(uint256 _commandId) public {
        CashInLib.Open memory command = database.retrieveCashInOpen(_commandId);
        require(database.cashInIsCreating(_commandId), "cash in must be in CREATING state");
        database.createCashInAccount(
            _commandId,
            command.maxBalance,
            AParameterManager(context.get(PARAMETER_MANAGER)).getVLFee(),
            command.update
        );
        database.setCashInStatus(_commandId, CashInLib.Status.ACTIVE);
        ASessionManager(context.get(SESSION_MANAGER)).setSessionHasActiveCashIn(command.sessionId);
        ACashInController(context.get(CONTROLLER)).respondOpened(command.sessionId, _commandId, command.success);
    }

    function updateCashInBalance(uint256 _channelId, uint256 _amount) public {
        require(msg.sender == context.get(ORACLE), "only oracle allowed");
        CashInLib.CashIn memory cashIn = database.retrieveCashIn(_channelId);
        CashInLib.Account memory account = database.retrieveCashInAccount(_channelId);
        require(account.balance.add(_amount) <= account.maxBalance, "cash-in balance overflow");
        database.setCashInAccountBalance(_channelId, account.balance.add(_amount));
        ACashInController(context.get(CONTROLLER)).respondUpdate(cashIn.sessionId, cashIn.id, _amount, account.update);
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
        returns (bool _accepted)
    {
        _validateCanBeClosed(_application, _sessionId, _channelId);
        CashInLib.Account memory account = database.retrieveCashInAccount(_channelId);
        uint256 vaultLogicFee = account.balance.mul(account.fee).div(10000);
        uint256 feesAmount = _sumOf(_fees);
        require(feesAmount.add(vaultLogicFee) <= account.balance, "Channel balance overflow");
        database.createCashInSplit(_channelId, _fees, _parties);
        database.setCashInStatus(_channelId, CashInLib.Status.CLOSE_REQUESTED);
        database.createCashInClose(_channelId, _sessionId, _success, _fail);
        _accepted = ACashInOracle(context.get(ORACLE)).onNextCloseCashIn(_channelId);
    }

    function _validateCanBeClosed(address _application, uint256 _sessionId, uint256 _cashInId) private view {
        CashInLib.CashIn memory cashIn = database.retrieveCashIn(_cashInId);
        require(database.cashInIsActive(_cashInId), "cash-in is not active");
        require(cashIn.application == _application, "illegal access");
        require(cashIn.sessionId == _sessionId, "illegal access");
    }

    function balanceOf(address _application, uint256 _channelId) public view returns (uint256) {
        ACashInStorage cashInStorage = ACashInStorage(context.get(CASH_IN_STORAGE));
        require(cashInStorage.getApplication(_channelId) == _application, "Illegal access");
        return cashInStorage.getBalance(_channelId);
    }

    function _sumOf(uint256[] _elements) private pure returns (uint256 _sum) {
        for (uint256 i = 0; i < _elements.length; i++) _sum = _sum.add(_elements[i]);
    }

    function confirmClose(uint256 _commandId) public {
        require(database.cashInInStatus(_commandId, CashInLib.Status.CLOSE_REQUESTED), "cash-in must be CLOSE_REQUESTED");
        CashInLib.CashIn memory cashIn = database.retrieveCashIn(_commandId);
        CashInLib.Close memory command = database.retrieveCashInClose(_commandId);
        CashInLib.Account memory account = database.retrieveCashInAccount(_commandId);
        CashInLib.Split memory split = database.retrieveCashInSplit(_commandId);
        ATokenManager tokenManager = ATokenManager(context.get(TOKEN_MANAGER));
        uint256 vaultLogicFee = account.balance.mul(account.fee).div(10000);
        if (split.size > 0) {
            uint256 sumOfSplitFees = _sumOf(split.fees);
            uint256 applicationBalance = account.balance.sub(vaultLogicFee).sub(sumOfSplitFees);
            tokenManager.transfer(cashIn.application, applicationBalance);
            for (uint256 i = 0; i < split.size; i++) tokenManager.transfer(split.parties[i], split.fees[i]);
        } else {
            tokenManager.transfer(cashIn.application, account.balance.sub(vaultLogicFee));
        }
        tokenManager.transfer(owner, vaultLogicFee);
        database.setCashInStatus(_commandId, CashInLib.Status.CLOSED);
        ASessionManager(context.get(SESSION_MANAGER)).setSessionIsNotHasActiveCashIn(command.sessionId);
        ACashInController(context.get(CONTROLLER)).respondClosed(command.sessionId, _commandId, command.success);
    }

}