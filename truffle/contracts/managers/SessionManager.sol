pragma solidity 0.4.24;

import {ASessionManager} from "./Managers.sol";
import {SessionLib} from "../libs/Libraries.sol";
import {ASessionOracle} from "../oracles/Oracles.sol";
import "../Platform.sol";

contract SessionManager is ASessionManager, Named("session-manager"), Mortal, Component {

    using SessionLib for address;

    string constant ORACLE = "session-oracle";

    constructor(address _config) Component(_config) public {}

    function createSession(uint256 _sessionId, uint256 _appId, string _xToken, string _kioskId) public {
        SessionLib.Session memory session = SessionLib.Session(_sessionId, _appId, _kioskId, _xToken, SessionLib.Status.CREATING, false, false);
        database.createSession(session);
        address deployedAddress = database.retrieveSessionApplicationDeployedAddress(_sessionId);
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
    }

    function isActive(uint256 _sessionId) public view returns (bool) {
        return database.getStatus(_sessionId) == SessionLib.Status.ACTIVE;
    }

    function isHasActiveCashIn(uint256 _sessionId) public view returns (bool) {
        return database.getIsHasActiveCashIn(_sessionId);
    }

    function activate(uint256 _sessionId) public {
        SessionLib.Status status = database.getStatus(_sessionId);
        require(status == SessionLib.Status.CREATING, "Session activation failed. Required CREATING state");
        database.setStatus(_sessionId, SessionLib.Status.ACTIVE);
    }

    function validateCanOpenCashIn(uint256 _sessionId, address _application) public view returns (bool _canOpenCashIn) {
        if (database.sessionIsNotActive(_sessionId)) return;
        if (database.sessionNotBelongsToApplication(_sessionId, _application)) return;
        if (database.sessionHasActiveCashIn(_sessionId)) return;
        _canOpenCashIn = true;
    }

    function setSessionHasActiveCashIn(uint256 _sessionId) public {
        database.setSessionHasActiveCashIn(_sessionId, true);
    }

    function setSessionIsNotHasActiveCashIn(uint256 _sessionId) public {
        database.setSessionHasActiveCashIn(_sessionId, false);
    }

}