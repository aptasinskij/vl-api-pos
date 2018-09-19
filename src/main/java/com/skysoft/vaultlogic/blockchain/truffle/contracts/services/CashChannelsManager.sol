pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../repositories/cash-in/ICashInStorage.sol";
import "../repositories/session/ISessionStorage.sol";
import "../repositories/application/IApplicationStorage.sol";
import "../application/IApplication.sol";
import "../oracles/ICashInOracle.sol";
import "./ISessionManager.sol";
import "./ICashChannelsManager.sol";
import "../libs/SafeMath.sol";
import "./ITokenManager.sol";

contract CashChannelsManager is RegistryComponent, ICashChannelsManager {

    enum CashInStatus { CREATING, ACTIVE, FAILED_TO_CREATE, CLOSE_REQUESTED, CLOSED, FAILED_TO_CLOSE }

    string constant COMPONENT_NAME = "cash-channels-manager";

    string constant CASH_IN_STORAGE = "cash-in-storage";
    string constant CASH_IN_ORACLE = "cash-in-oracle";
    string constant SESSION_STORAGE = "session-storage";
    string constant APPLICATION_STORAGE = "application-storage";
    string constant SESSION_MANAGER = "session-manager";
    string constant TOKEN_MANAGER = "token-manager";

    using SafeMath for uint256;

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string name) {
        return COMPONENT_NAME;
    }

    ///@dev cash-in channel could be created only if:
    ///session is active; application owns the session; there is no active channels in the session
    function openCashInChannel(address _application, uint256 _sessionId) public returns(uint256 channelId) {
        require(_sessionManager().isActive(_sessionId), "Illegal state of the session");
        require(!_sessionManager().isHasActiveCashIn(_sessionId), "There is already opened cash-in channel");
        address application = _applicationStorage().getApplicationAddress(_sessionStorage().getAppId(_sessionId));
        require(application == _application, "Illegal access");
        channelId = _cashInStorage().save(_sessionId, application, uint256(CashInStatus.CREATING));
        ICashInOracle(lookup(CASH_IN_ORACLE)).open(_sessionId, channelId, uint256(CashInStatus.CREATING));
    }

    function updateCashInBalance(uint256 channelId, uint256 amount) external {
        uint256 currentBalance = _cashInStorage().getBalance(channelId);
        _cashInStorage().setBalance(channelId, currentBalance + amount);
        (address application, uint256 sessionId) = _cashInStorage().getApplicationAndSessionId(channelId);
        IApplication(application).cashInBalanceUpdate(channelId, sessionId, amount);
    }

    function closeCashInChannel(address _application, uint256 _sessionId, uint256 _channelId, uint256[] fees, address[] parties) external {
        require(_cashInStorage().getStatus() == uint256(CashInStatus.ACTIVE));
        require(_cashInStorage().getApplication(_channelId) == _application, "Illegal access");
        require(fees.length == parties.length, "Illegal arguments");
        uint256 channelBalance = _cashInStorage().getBalance(_channelId);
        uint256 vaultLogicFee = channelBalance.mul(getVaultLogicPercent()).div(10000);
        uint256 feesAmount = 0;
        for (uint256 i = 0; i < fees.length; i++) feesAmount = feesAmount.add(fees[i]);
        require(feesAmount.add(vaultLogicFee) <= channelBalance, "Channel balance overflow");
        _tokenManager().transfer(_application, channelBalance.sub(vaultLogicFee).sub(feesAmount));
        for (uint256 j = 0; j < parties.length; j++) _tokenManager().transfer(parties[j], fees[j]);
        _cashInStorage().setStatus(_channelId, uint256(CashInStatus.CLOSE_REQUESTED));
        ICashInOracle(lookup(CASH_IN_ORACLE)).close(_sessionId, _channelId);
    }

    function confirmOpen(uint256 channelId) external {
        (address application, uint256 sessionId) = _cashInStorage().getApplicationAndSessionId(channelId);
        _cashInStorage().setStatus(channelId, uint256(CashInStatus.ACTIVE));
        _sessionStorage().setHasActiveCashIn(sessionId, true);
        IApplication(application).cashInChannelOpened(channelId, sessionId);
    }

    function confirmClose(uint256 channelId) external {
        (address application, uint256 sessionId) = _cashInStorage().getApplicationAndSessionId(channelId);
        _cashInStorage().setStatus(channelId, uint256(CashInStatus.CLOSED));
        IApplication(application).cashInChannelClosed(channelId, sessionId);
    }

    //TODO where to get actual number
    function getVaultLogicPercent() private pure returns(uint256) {
        return 1000;
    }

    function _tokenManager() private view returns(ITokenManager) {
        return ITokenManager(lookup(TOKEN_MANAGER));
    }

    function _sessionManager() private view returns(ISessionManager) {
        return ISessionManager(lookup(SESSION_MANAGER));
    }

    function _sessionStorage() private view returns(ISessionStorage) {
        return ISessionStorage(lookup(SESSION_STORAGE));
    }

    function _applicationStorage() private view returns(IApplicationStorage) {
        return IApplicationStorage(lookup(APPLICATION_STORAGE));
    }

    function _cashInStorage() private view returns(ICashInStorage) {
        return ICashInStorage(lookup(CASH_IN_STORAGE));
    }

}