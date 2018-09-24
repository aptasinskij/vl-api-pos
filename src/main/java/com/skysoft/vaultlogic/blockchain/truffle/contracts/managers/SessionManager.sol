pragma solidity 0.4.24;

import "../application/IApplication.sol";
import "../oracles/ISessionOracle.sol";
import "../application/IApplication.sol";
import "../registry/Component.sol";
import {ASessionManager} from "./Managers.sol";

contract SessionManager is Component, ASessionManager {
    
    enum SessionStatus { CREATING, ACTIVE, FAILED_TO_CREATE, CLOSE_REQUESTED, CLOSED }

    string constant COMPONENT_NAME = "session-manager";

    constructor(address regAddr) Component(regAddr) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function createSession(uint256 sessionId, uint256 appId, string xToken) public {
        _sessionStorage().save(sessionId, appId, xToken, uint256(SessionStatus.CREATING));
        address appAddress = _applicationStorage().getApplicationAddress(appId);
        IApplication(appAddress).newSessionCreated();
    }

    function closeSession(uint256 sessionId) public {
        uint256 status = ASessionStorage(lookup(SESSION_STORAGE)).getStatus(sessionId);
        require(status == uint256(SessionStatus.ACTIVE), "Illegal state modification");
        _sessionStorage().setStatus(sessionId, uint256(SessionStatus.CLOSE_REQUESTED));
        _sessionOracle().closeSession(sessionId);
    }

    function confirmClose(uint256 sessionId) public {
        _sessionStorage().setStatus(sessionId, uint256(SessionStatus.CLOSED));
        address appAddress = _applicationStorage().getApplicationAddress(_sessionStorage().getAppId(sessionId));
        IApplication(appAddress).sessionClosed(sessionId);
    }

    function isActive(uint256 sessionId) public view returns(bool) {
        return (_sessionStorage().getStatus(sessionId) == uint256(SessionStatus.ACTIVE));
    }

    function isHasActiveCashIn(uint256 _sessionId) public view returns(bool) {
        return _sessionStorage().isHasActiveCashIn(_sessionId);
    }

    function activate(uint256 _sessionId) public returns(bool) {
        require(_sessionStorage().getStatus(_sessionId) == uint256(SessionStatus.CREATING), "Illegal state modification");
        _sessionStorage().setStatus(_sessionId, uint256(SessionStatus.ACTIVE));
        return true;
    }

}