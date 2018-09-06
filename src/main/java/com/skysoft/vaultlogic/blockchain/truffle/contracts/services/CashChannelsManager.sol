pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../repositories/cash-in/CashInStorageApi.sol";
import "../repositories/session/SessionRepositoryApi.sol";
import "../repositories/application/ApplicationRepositoryApi.sol";
import "../application/ApplicationApi.sol";
import "../oracles/CashAcceptorOracleApi.sol";

contract CashChannelsManager is RegistryComponent {

    enum CashInStatus { PENDING, OPENED, HALF_CLOSED, CLOSED }

    string constant COMPONENT_NAME = "cash-channels-manager";

    string constant CASH_IN_REPOSITORY = "cash-in-storage";
    string constant CASH_ACCEPTOR_ORACLE = "cash-acceptor-oracle";
    string constant SESSION_REPOSITORY = "session-repository";
    string constant APPLICATION_REPOSITORY = "application-repository";

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string name) {
        return COMPONENT_NAME;
    }

    function openCashInChannel(uint256 sessionId) external returns(uint256) {
        (uint256 appId, string memory xToken) = SessionRepositoryApi(lookup(SESSION_REPOSITORY)).getAppIdAndXToken(sessionId);
        address application = ApplicationRepositoryApi(lookup(APPLICATION_REPOSITORY)).getApplicationAddress(appId);
        uint256 channelId = CashInStorageApi(lookup(CASH_IN_REPOSITORY)).save(sessionId, application, uint256(CashInStatus.PENDING));
        CashAcceptorOracleApi(lookup(CASH_ACCEPTOR_ORACLE)).open(xToken, sessionId, channelId);
        return channelId;
    }

    function closeCashInChannel(uint256 sessionId, uint256 channelId) external {
        string memory xToken = SessionRepositoryApi(lookup(SESSION_REPOSITORY)).getXToken(sessionId);
        CashInStorageApi(lookup(CASH_IN_REPOSITORY)).setStatus(channelId, uint256(CashInStatus.HALF_CLOSED));
        CashAcceptorOracleApi(lookup(CASH_ACCEPTOR_ORACLE)).close(xToken, sessionId, channelId);
    }

    function confirmOpen(uint256 channelId) external {
        CashInStorageApi cashInRepo = CashInStorageApi(lookup(CASH_IN_REPOSITORY));
        (address application, uint256 sessionId) = cashInRepo.getApplicationAndSessionId(channelId);
        cashInRepo.setStatus(channelId, uint256(CashInStatus.OPENED));
        ApplicationApi(application).cashInChannelOpened(channelId, sessionId);
    }

    function confirmClose(uint256 channelId) external {
        CashInStorageApi cashInRepo = CashInStorageApi(lookup(CASH_IN_REPOSITORY));
        (address application, uint256 sessionId) = cashInRepo.getApplicationAndSessionId(channelId);
        cashInRepo.setStatus(channelId, uint256(CashInStatus.CLOSED));
        ApplicationApi(application).cashInChannelClosed(channelId, sessionId);
    }

}