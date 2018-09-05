pragma solidity 0.4.24;

import "../application/ApplicationApi.sol";
import "../oracles/SessionOracleApi.sol";
import "../application/ApplicationApi.sol";
import "../registry/RegistryComponent.sol";
import "../repositories/session/SessionRepositoryApi.sol";
import "../repositories/application/ApplicationRepositoryApi.sol";

contract SessionService is RegistryComponent {
    
    enum SessionStatus { ACTIVE, CLOSE_REQUESTED, CLOSED }

    string constant COMPONENT_NAME = "session-service";

    string constant SESSION_REPOSITORY = "session-repository";
    string constant APPLICATION_REPOSITORY = "application-repository";

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function createSession(uint256 id, uint256 appId, string xToken) external {
        SessionRepositoryApi(lookup(SESSION_REPOSITORY)).save(id, appId, xToken, uint256(SessionStatus.ACTIVE));
        address appAddress = ApplicationRepositoryApi(lookup(APPLICATION_REPOSITORY)).getApplicationAddress(appId);
        ApplicationApi(appAddress).newSessionCreated();
    }

    function closeSession(uint256 id) external {
        (uint256 status, string memory xToken) = SessionRepositoryApi(lookup(SESSION_REPOSITORY)).getStatusAndXToken(id);
        require(status == uint256(SessionStatus.ACTIVE), "Illegal state modification");
        SessionRepositoryApi(lookup(SESSION_REPOSITORY)).setStatus(id, uint256(SessionStatus.CLOSE_REQUESTED));
        SessionOracleApi(lookup("session-oracle")).closeSession(xToken);
    }

    function confirmClose(uint256 id) external {
        SessionRepositoryApi sessionRepo = SessionRepositoryApi(lookup(SESSION_REPOSITORY));
        sessionRepo.setStatus(id, uint256(SessionStatus.CLOSED));
        address appAddress = ApplicationRepositoryApi(lookup(APPLICATION_REPOSITORY)).getApplicationAddress(sessionRepo.getAppId(id));
        ApplicationApi(appAddress).sessionClosed(id);
    }

}