pragma solidity 0.4.24;

import "../application/IApplication.sol";
import {ACashInOracle} from "../oracles/Oracles.sol";
import "../libs/Libraries.sol";
import {ACashChannelsManager, ASessionManager, ATokenManager} from "./Managers.sol";
import "../storages/Storages.sol";
import "../Platform.sol";
import {ACashInController} from "../controllers/Controllers.sol";

contract CashChannelsManager is ACashChannelsManager, Named("cash-channels-manager"), Mortal, Component {

    using SafeMath for uint256;
    using CashInLib for *;
    using CashInOpenLib for address;
    using CashInAccountLib for address;
    using ParameterLib for address;
    using CashInSplit for address;
    using CashInClose for address;

    string constant CASH_IN_STORAGE = "cash-in-storage";
    string constant SESSION_STORAGE = "session-storage";
    string constant APPLICATION_STORAGE = "application-storage";
    string constant PARAMETER_STORAGE = "parameter-storage";
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
        require(database.sessionIsActive(_sessionId), "non active session");
        require(database.retrieveSessionApplicationDeployedAddress(_sessionId) == _application, "illegal session access");
        require(!database.getIsHasActiveCashIn(_sessionId), "there is already cash in channel in session");
        database.setSessionHasActiveCashIn(_sessionId, true);
        CashInLib.CashIn memory cashIn = CashInLib.CashIn(database.getNextCashInId(), _sessionId, _application, CashInLib.Status.CREATING);
        CashInOpenLib.OpenCashIn memory openCashIn = CashInOpenLib.CashInOpen(database.getNextOpenCashInId(), _sessionId, cashIn.id, _maxBalance, _success, _update, _fail);
        database.createCashIn(cashIn);
        database.createOpenCashIn(openCashIn);
        _accepted = ACashInOracle(context.get(ORACLE)).onNextOpenCashIn(openCashIn.id);
    }

    function confirmOpen(uint256 _commandId) public {
        CashInOpenLib.OpenCashIn memory command = database.retrieveOpenCashIn(_commandId);
        CashInLib.CashIn memory cashIn = database.retrieveCashIn(command.cashInId);
        require(cashIn.isCreating(), "cash in must be in CREATING state");
        CashInAccountLib.Account memory account = CashInAccountLib.Account(database.getNextCashInAccountId(), cashIn.id, 0, _maxBalance, database.getVLFee(), 0, 0, command.update);
        database.createCashInAccount(account);
        database.setCashInStatus(cashIn.id, CashInLib.Status.ACTIVE);
        ACashInController(context.get(CONTROLLER)).respondOpened(cashIn.sessionId, cashIn.id, command.success);
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
        require(_fees.length == _parties.length, "Illegal arguments");
        CashInLib.CashIn memory cashIn = database.retrieveCashIn(_channelId);
        require(cashIn.isActive(), "cash-in is not active");
        require(cashIn.application == _application, "illegal access");
        require(cashIn.sessionId == _sessionId, "illegal access");
        CashInAccountLib.Account memory account = database.retrieveCashInAccountByCashInId(cashIn.id);
        uint256 vaultLogicFee = account.balance.mul(account.vaultLogicPercent).div(10000);
        uint256 feesAmount = _sumOf(_fees);
        require(feesAmount.add(vaultLogicFee) <= account.balance, "Channel balance overflow");
        database.setAccountVaultLogicBalance(account.id, vaultLogicFee);
        database.setAccountApplicationBalance(account.id, account.balance.sub(vaultLogicFee).sub(feesAmount));
        database.createCashInSplit(cashIn.id, _fees, _parties);
        database.setCashInStatus(cashIn.id, CashInLib.Status.CLOSE_REQUESTED);
        uint256 commandId = database.createCashInClose(cashIn.id, _sessionId, _success, _fail);
        _accepted = ACashInOracle(context.get(ORACLE)).onNextCloseCashIn(commandId);
    }

    function updateCashInBalance(uint256 channelId, uint256 amount) public cashInActive(channelId) {
        uint256 currentBalance = ACashInStorage(context.get(CASH_IN_STORAGE)).getBalance(channelId);
        ACashInStorage(context.get(CASH_IN_STORAGE)).setBalance(channelId, currentBalance + amount);
        (address application, uint256 sessionId) = ACashInStorage(context.get(CASH_IN_STORAGE)).getApplicationAndSessionId(channelId);
        IApplication(application).cashInBalanceUpdate(channelId, amount, sessionId);
    }

    function balanceOf(address _application, uint256 _channelId) public view returns (uint256) {
        ACashInStorage cashInStorage = ACashInStorage(context.get(CASH_IN_STORAGE));
        require(cashInStorage.getApplication(_channelId) == _application, "Illegal access");
        return cashInStorage.getBalance(_channelId);
    }



    function _sumOf(uint256[] _elements) private pure returns (uint256 _sum) {
        for (uint256 i = 0; i < _elements.length; i++) _sum = _sum.add(_elements[i]);
    }

    function _transferAll(address[] _recepients, uint256[] _amounts) private {
        for (uint256 j = 0; j < _recepients.length; j++) _transfer(_recepients[j], _amounts[j]);
    }

    function _transfer(address _recepient, uint256 amount) private {
        ATokenManager(context.get(TOKEN_MANAGER)).transfer(_recepient, amount);
    }

    function confirmClose(uint256 channelId) public {
        require(ACashInStorage(context.get(CASH_IN_STORAGE)).getStatus(channelId) == uint256(CashInStatus.CLOSE_REQUESTED));
        (address application, uint256 sessionId) = ACashInStorage(context.get(CASH_IN_STORAGE)).getApplicationAndSessionId(channelId);
        _transfer(owner, ACashInStorage(context.get(CASH_IN_STORAGE)).getVLFee(channelId));
        uint256 applicationBalance = ACashInStorage(context.get(CASH_IN_STORAGE)).getApplicationBalance(channelId);
        if (applicationBalance > 0) _transfer(application, applicationBalance);
        for (uint256 i = 0; i < ACashInStorage(context.get(CASH_IN_STORAGE)).getSplitSize(channelId); i++) {
            (address party, uint256 fee) = ACashInStorage(context.get(CASH_IN_STORAGE)).getSplit(channelId, i);
            _transfer(party, fee);
        }
        ACashInStorage(context.get(CASH_IN_STORAGE)).setStatus(channelId, uint256(CashInStatus.CLOSED));
        ASessionStorage(context.get(SESSION_STORAGE)).setHasActiveCashIn(sessionId, false);
        IApplication(application).cashInChannelClosed(channelId, sessionId);
    }

}