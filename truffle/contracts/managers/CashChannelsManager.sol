pragma solidity 0.4.24;

import "../application/IApplication.sol";
import {ACashInOracle} from "../oracles/Oracles.sol";
import {SafeMath} from "../libs/Libraries.sol";
import {ACashChannelsManager, ASessionManager, ATokenManager} from "./Managers.sol";
import "../storages/Storages.sol";
import "../Platform.sol";

contract CashChannelsManager is ACashChannelsManager, Named("cash-channels-manager"), Mortal, Component {

    using SafeMath for uint256;

    string constant CASH_IN_STORAGE = "cash-in-storage";
    string constant SESSION_STORAGE = "session-storage";
    string constant APPLICATION_STORAGE = "application-storage";
    string constant PARAMETER_STORAGE = "parameter-storage";
    string constant SESSION_MANAGER = "session-manager";
    string constant TOKEN_MANAGER = "token-manager";
    string constant CASH_IN_ORACLE = "cash-in-oracle";

    modifier cashInActive(uint256 _channelId) {
        require(ACashInStorage(context.get(CASH_IN_STORAGE)).getStatus(_channelId) == uint256(CashInStatus.ACTIVE), "CashIn in illegal state");
        _;
    }

    modifier appOwnsChannel(uint256 _channelId, address _application) {
        require(ACashInStorage(context.get(CASH_IN_STORAGE)).getApplication(_channelId) == _application, "Illegal access");
        _;
    }

    modifier channelBelongsToSession(uint256 _channelId, uint256 _sessionId) {
        require(ACashInStorage(context.get(CASH_IN_STORAGE)).getSessionId(_channelId) == _sessionId, "Arguments mismatch");
        _;
    }

    constructor(address _config) Component(_config) public {}

    ///@dev cash-in channel could be created only if:
    ///session is active; application owns the session; there is no active channels in the session
    function openCashInChannel(address _application, uint256 _sessionId) public returns (uint256 channelId) {
        require(ASessionManager(context.get(SESSION_MANAGER)).isActive(_sessionId), "Illegal state of the session");
        require(!ASessionStorage(context.get(SESSION_STORAGE)).isHasActiveCashIn(_sessionId), "There is already opened cash-in channel");
        ASessionStorage(context.get(SESSION_STORAGE)).setHasActiveCashIn(_sessionId, true);
        address application = AnApplicationStorage(context.get(APPLICATION_STORAGE)).getApplicationAddress(ASessionStorage(context.get(SESSION_STORAGE)).getAppId(_sessionId));
        require(application == _application, "Illegal access");
        channelId = ACashInStorage(context.get(CASH_IN_STORAGE)).save(_sessionId, application, uint256(CashInStatus.CREATING));
        ACashInOracle(context.get(CASH_IN_ORACLE)).open(_sessionId, channelId, uint256(CashInStatus.CREATING));
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

    function closeCashInChannel(address _application, uint256 _sessionId, uint256 _channelId, uint256[] fees, address[] parties)
    cashInActive(_channelId)
    appOwnsChannel(_channelId, _application)
    channelBelongsToSession(_channelId, _sessionId)
    public returns (bool){
        require(fees.length == parties.length, "Illegal arguments");
        uint256 channelBalance = ACashInStorage(context.get(CASH_IN_STORAGE)).getBalance(_channelId);
        uint256 vaultLogicFee = channelBalance.mul(AParameterStorage(context.get(PARAMETER_STORAGE)).getVLFee()).div(10000);
        uint256 feesAmount = _sumOf(fees);
        require(feesAmount.add(vaultLogicFee) <= channelBalance, "Channel balance overflow");
        ACashInStorage(context.get(CASH_IN_STORAGE)).setVLFee(_channelId, vaultLogicFee);
        ACashInStorage(context.get(CASH_IN_STORAGE)).setApplicationBalance(_channelId, channelBalance.sub(vaultLogicFee).sub(feesAmount));
        ACashInStorage(context.get(CASH_IN_STORAGE)).addSplits(_channelId, parties, fees);
        ACashInStorage(context.get(CASH_IN_STORAGE)).setStatus(_channelId, uint256(CashInStatus.CLOSE_REQUESTED));
        ACashInOracle(context.get(CASH_IN_ORACLE)).close(_sessionId, _channelId);
        return true;
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

    function confirmOpen(uint256 channelId) public {
        require(ACashInStorage(context.get(CASH_IN_STORAGE)).getStatus(channelId) == uint256(CashInStatus.CREATING));
        (address application, uint256 sessionId) = ACashInStorage(context.get(CASH_IN_STORAGE)).getApplicationAndSessionId(channelId);
        ACashInStorage(context.get(CASH_IN_STORAGE)).setStatus(channelId, uint256(CashInStatus.ACTIVE));
        IApplication(application).cashInChannelOpened(channelId, sessionId);
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