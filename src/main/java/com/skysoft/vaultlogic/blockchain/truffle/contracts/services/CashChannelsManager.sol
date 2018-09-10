pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../repositories/cash-in/ICashInStorage.sol";
import "../repositories/session/ISessionStorage.sol";
import "../repositories/application/IApplicationStorage.sol";
import "../application/IApplication.sol";
import "../oracles/ICashInOracle.sol";

contract CashChannelsManager is RegistryComponent {

    enum CashInStatus { PENDING, OPENED, HALF_CLOSED, CLOSED }

    string constant COMPONENT_NAME = "cash-channels-manager";

    string constant CASH_IN_STORAGE = "cash-in-storage";
    string constant CASH_IN_ORACLE = "cash-in-oracle";
    string constant SESSION_STORAGE = "session-storage";
    string constant APPLICATION_STORAGE = "application-storage";

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string name) {
        return COMPONENT_NAME;
    }

    function openCashInChannel(uint256 sessionId) external returns(uint256) {
        uint256 appId = ISessionStorage(lookup(SESSION_STORAGE)).getAppId(sessionId);
        address application = IApplicationStorage(lookup(APPLICATION_STORAGE)).getApplicationAddress(appId);
        uint256 channelId = ICashInStorage(lookup(CASH_IN_STORAGE)).save(sessionId, application, uint256(CashInStatus.PENDING));
        ICashInOracle(lookup(CASH_IN_ORACLE)).open(sessionId, channelId, uint256(CashInStatus.PENDING));
        return channelId;
    }

    function updateCashInBalance(uint256 channelId, uint256 amount) external {
        ICashInStorage cashInStorage = ICashInStorage(lookup(CASH_IN_STORAGE));
        uint256 currentBalance = cashInStorage.getBalance(channelId);
        cashInStorage.setBalance(channelId, currentBalance + amount);
        (address application, uint256 sessionId) = cashInStorage.getApplicationAndSessionId(channelId);
        IApplication(application).cashInBalanceUpdate(channelId, sessionId, amount);
    }

    function closeCashInChannel(uint256 sessionId, uint256 channelId) external {
        string memory xToken = ISessionStorage(lookup(SESSION_STORAGE)).getXToken(sessionId);
        ICashInStorage(lookup(CASH_IN_STORAGE)).setStatus(channelId, uint256(CashInStatus.HALF_CLOSED));
        ICashInOracle(lookup(CASH_IN_ORACLE)).close(xToken, sessionId, channelId);
    }

    function confirmOpen(uint256 channelId) external {
        ICashInStorage cashInStorage = ICashInStorage(lookup(CASH_IN_STORAGE));
        (address application, uint256 sessionId) = cashInStorage.getApplicationAndSessionId(channelId);
        cashInStorage.setStatus(channelId, uint256(CashInStatus.OPENED));
        IApplication(application).cashInChannelOpened(channelId, sessionId);
    }

    function confirmClose(uint256 channelId) external {
        ICashInStorage cashInRepo = ICashInStorage(lookup(CASH_IN_STORAGE));
        (address application, uint256 sessionId) = cashInRepo.getApplicationAndSessionId(channelId);
        cashInRepo.setStatus(channelId, uint256(CashInStatus.CLOSED));
        IApplication(application).cashInChannelClosed(channelId, sessionId);
    }

}