pragma solidity 0.4.24;

import "../application/IApplication.sol";
import "../oracles/ISessionOracle.sol";
import "../application/IApplication.sol";
import "../registry/RegistryComponent.sol";
import "../repositories/session/ISessionStorage.sol";
import "../repositories/application/IApplicationStorage.sol";

contract SessionManager is RegistryComponent {
    
    enum SessionStatus { CREATING, ACTIVE, FAILED_TO_CREATE, CLOSE_REQUESTED, CLOSED }

    string constant COMPONENT_NAME = "session-manager";

    string constant SESSION_STORAGE = "session-storage";
    string constant APPLICATION_STORAGE = "application-storage";

    string constant SESSION_ORACLE = "session-oracle";

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function createSession(uint256 sessionId, uint256 appId, string xToken) external {
        ISessionStorage(lookup(SESSION_STORAGE)).save(sessionId, appId, xToken, uint256(SessionStatus.ACTIVE));
        address appAddress = IApplicationStorage(lookup(APPLICATION_STORAGE)).getApplicationAddress(appId);
        IApplication(appAddress).newSessionCreated();
    }

    function closeSession(uint256 sessionId) external {
        uint256 status = ISessionStorage(lookup(SESSION_STORAGE)).getStatus(sessionId);
        require(status == uint256(SessionStatus.ACTIVE), "Illegal state modification");
        ISessionStorage(lookup(SESSION_STORAGE)).setStatus(sessionId, uint256(SessionStatus.CLOSE_REQUESTED));
        ISessionOracle(lookup(SESSION_ORACLE)).closeSession(sessionId);
    }

    function confirmClose(uint256 sessionId) external {
        ISessionStorage sessionStorage = ISessionStorage(lookup(SESSION_STORAGE));
        sessionStorage.setStatus(sessionId, uint256(SessionStatus.CLOSED));
        address appAddress = IApplicationStorage(lookup(APPLICATION_STORAGE)).getApplicationAddress(sessionStorage.getAppId(sessionId));
        IApplication(appAddress).sessionClosed(sessionId);
    }

    function isActive(uint256 sessionId) public view returns(bool) {
        return (ISessionStorage(lookup(SESSION_STORAGE)).getStatus(sessionId) == uint256(SessionStatus.ACTIVE));
    }

}