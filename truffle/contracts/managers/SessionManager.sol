pragma solidity 0.4.24;

import "../application/IApplication.sol";
import {ASessionManager} from "./Managers.sol";
import {SessionLib} from "../libs/Libraries.sol";
import {ASessionOracle} from "../oracles/Oracles.sol";
import "../Platform.sol";

contract SessionManager is ASessionManager, Named("session-manager"), Mortal, Component {

    using SessionLib for address;

    string constant ORACLE = "session-oracle";

    constructor(address regAddr) Component(regAddr) public {}

    function createSession(uint256 _sessionId, uint256 _appId, string _xToken, string _kioskId) public {
        SessionLib.Session memory session = SessionLib.Session(_sessionId, _appId, _kioskId, _xToken, SessionLib.Status.CREATING, false, false);
        database.createSession(session);
        address deployedAddress = database.retrieveSessionApplicationDeployedAddress(_sessionId);
        IApplication(deployedAddress).newSessionCreated(_sessionId);
    }

    function closeSession(uint256 _sessionId) public {
        SessionLib.Status status = database.getStatus(_sessionId);
        require(status == SessionLib.Status.ACTIVE, "Session request close failed. Required ACTIVE state.");
        database.setStatus(_sessionId, SessionLib.Status.CLOSE_REQUESTED);
        ASessionOracle(context.get(ORACLE)).closeSession(_sessionId);
    }

    function confirmClose(uint256 _sessionId) public {
        SessionLib.Status status = database.getStatus(_sessionId);
        require(status == SessionLib.Status.CLOSE_REQUESTED, "Session confirm close failed. Required CLOSE_REQUESTED state.");
        database.setStatus(_sessionId, SessionLib.Status.CLOSED);
        address deployedAddress = database.retrieveSessionApplicationDeployedAddress(_sessionId);
        IApplication(deployedAddress).sessionClosed(_sessionId);
    }

    function isActive(uint256 _sessionId) public view returns (bool) {
        return database.getStatus(_sessionId) == SessionLib.Status.ACTIVE;
    }

    function isHasActiveCashIn(uint256 _sessionId) public view returns (bool) {
        return database.getIsHasActiveCashIn(_sessionId);
    }

    function activate(uint256 _sessionId) public returns (bool) {
        SessionLib.Status status = database.getStatus(_sessionId);
        require(status == SessionLib.Status.CREATING, "Session activation failed. Required CREATING state");
        database.setStatus(_sessionId, SessionLib.Status.ACTIVE);
        return true;
    }

}