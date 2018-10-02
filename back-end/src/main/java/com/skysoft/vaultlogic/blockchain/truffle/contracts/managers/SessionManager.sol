pragma solidity 0.4.24;

import "../application/IApplication.sol";
import "../oracles/ISessionOracle.sol";
import "../application/IApplication.sol";
import "../registry/Component.sol";
import {ASessionManager} from "./Managers.sol";
import {SessionLib} from "../libs/Libraries.sol";

contract SessionManager is Component, ASessionManager {

    using SessionLib for address;

    string constant COMPONENT_NAME = "session-manager";

    constructor(address regAddr) Component(regAddr) public {}

    function getName() internal pure returns (string) {
        return COMPONENT_NAME;
    }

    function createSession(uint256 _sessionId, uint256 _appId, string _xToken, string _kioskId) public {
        SessionLib.Session memory session = SessionLib.Session(_sessionId, _appId, _kioskId, _xToken, SessionLib.Status.CREATING, false, false);
        _database().createSession(session);
        address deployedAddress = _database().retrieveSessionApplicationDeployedAddress(_sessionId);
        IApplication(deployedAddress).newSessionCreated(_sessionId);
    }

    function closeSession(uint256 _sessionId) public {
        SessionLib.Status status = _database().getStatus(_sessionId);
        require(status == SessionLib.Status.ACTIVE, "Session request close failed. Required ACTIVE state.");
        _database().setStatus(_sessionId, SessionLib.Status.CLOSE_REQUESTED);
        _sessionOracle().closeSession(_sessionId);
    }

    function confirmClose(uint256 _sessionId) public {
        SessionLib.Status status = _database().getStatus(_sessionId);
        require(status == SessionLib.Status.CLOSE_REQUESTED, "Session confirm close failed. Required CLOSE_REQUESTED state.");
        _database().setStatus(_sessionId, SessionLib.Status.CLOSED);
        address deployedAddress = _database().retrieveSessionApplicationDeployedAddress(_sessionId);
        IApplication(deployedAddress).sessionClosed(_sessionId);
    }

    function isActive(uint256 _sessionId) public view returns (bool) {
        return _database().getStatus(_sessionId) == SessionLib.Status.ACTIVE;
    }

    function isHasActiveCashIn(uint256 _sessionId) public view returns (bool) {
        return _database().getIsHasActiveCashIn(_sessionId);
    }

    function activate(uint256 _sessionId) public returns (bool) {
        SessionLib.Status status = _database().getStatus(_sessionId);
        require(status == SessionLib.Status.CREATING, "Illegal state modification");
        _database().setStatus(_sessionId, SessionLib.Status.ACTIVE);
        return true;
    }

}