pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../repositories/cash-in/ICashInStorage.sol";
import "../repositories/session/ISessionStorage.sol";
import "../repositories/application/IApplicationStorage.sol";
import "../application/IApplication.sol";
import "../oracles/ICashInOracle.sol";
import "./ISessionManager.sol";
import "./ICashChannelsManager.sol";

contract CashChannelsManager is RegistryComponent, ICashChannelsManager {

    enum CashInStatus { CREATING, ACTIVE, FAILED_TO_CREATE, CLOSE_REQUESTED, CLOSED, FAILED_TO_CLOSE }

    string constant COMPONENT_NAME = "cash-channels-manager";

    string constant CASH_IN_STORAGE = "cash-in-storage";
    string constant CASH_IN_ORACLE = "cash-in-oracle";
    string constant SESSION_STORAGE = "session-storage";
    string constant APPLICATION_STORAGE = "application-storage";
    string constant SESSION_MANAGER = "session-manager";

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string name) {
        return COMPONENT_NAME;
    }

    ///@dev cash-in channel could be created only if:
    ///session is active; application owns the session; there is no active channels in the session
    function openCashInChannel(uint256 _sessionId, address _application) external returns(uint256 channelId) {
        require(getSessionManager().isActive(_sessionId), "Illegal state of the session");
        ISessionStorage sessionStorage = getSessionStorage();
        address application = getApplicationStorage().getApplicationAddress(sessionStorage.getAppId(_sessionId));
        require(application == _application, "Illegal access");
        channelId = getCashInStorage().save(_sessionId, application, uint256(CashInStatus.CREATING));
        ICashInOracle(lookup(CASH_IN_ORACLE)).open(_sessionId, channelId, uint256(CashInStatus.CREATING));
    }

    function updateCashInBalance(uint256 channelId, uint256 amount) external {
        ICashInStorage cashInStorage = ICashInStorage(lookup(CASH_IN_STORAGE));
        uint256 currentBalance = cashInStorage.getBalance(channelId);
        cashInStorage.setBalance(channelId, currentBalance + amount);
        (address application, uint256 sessionId) = cashInStorage.getApplicationAndSessionId(channelId);
        IApplication(application).cashInBalanceUpdate(channelId, sessionId, amount);
    }

    function closeCashInChannel(uint256 sessionId, uint256 channelId) external {
        ICashInStorage(lookup(CASH_IN_STORAGE)).setStatus(channelId, uint256(CashInStatus.CLOSE_REQUESTED));
        ICashInOracle(lookup(CASH_IN_ORACLE)).close(sessionId, channelId);
    }

    function confirmOpen(uint256 channelId) external {
        ICashInStorage cashInStorage = ICashInStorage(lookup(CASH_IN_STORAGE));
        (address application, uint256 sessionId) = cashInStorage.getApplicationAndSessionId(channelId);
        cashInStorage.setStatus(channelId, uint256(CashInStatus.ACTIVE));
        IApplication(application).cashInChannelOpened(channelId, sessionId);
    }

    function confirmClose(uint256 channelId) external {
        ICashInStorage cashInRepo = ICashInStorage(lookup(CASH_IN_STORAGE));
        (address application, uint256 sessionId) = cashInRepo.getApplicationAndSessionId(channelId);
        cashInRepo.setStatus(channelId, uint256(CashInStatus.CLOSED));
        IApplication(application).cashInChannelClosed(channelId, sessionId);
    }

    function getSessionManager() private returns(ISessionManager) {
        return ISessionManager(lookup(SESSION_MANAGER));
    }

    function getSessionStorage() private view returns(ISessionStorage) {
        return ISessionStorage(lookup(SESSION_STORAGE));
    }

    function getApplicationStorage() private view returns(IApplicationStorage) {
        return IApplicationStorage(lookup(APPLICATION_STORAGE));
    }

    function getCashInStorage() private view returns(ICashInStorage) {
        return ICashInStorage(lookup(CASH_IN_STORAGE));
    }

}