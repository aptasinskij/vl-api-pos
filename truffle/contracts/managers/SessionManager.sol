pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/ASessionManager.sol";
import "../oracles/api/ASessionOracle.sol";
import "../storages/libs/SessionLib.sol";
import "../storages/api/ASessionStorage.sol";
import "../controllers/api/ASessionController.sol";

contract SessionManager is ASessionManager, Named("session-manager"), Mortal, Component {

    string constant ORACLE = "session-oracle";
    string constant CONTROLLER = "session-controller";
    string constant STORAGE = "session-storage";

    constructor(address _config) Component(_config) public {}

    function createSession(uint256 _sessionId, uint256 _appId, string _xToken, string _kioskId) public {
        //TODO:implementation: check if msg.sender is ORACLE
        ASessionStorage(context.get(STORAGE)).createSession(_sessionId, _appId, _kioskId, _xToken);
    }

    function activate(uint256 _sessionId) public {
        //TODO:implementation: check if msg.sender is ORACLE
        ASessionStorage sessionStorage = ASessionStorage(context.get(STORAGE));
        SessionLib.Status status = sessionStorage.retrieveSessionStatus(_sessionId);
        require(status == SessionLib.Status.CREATING, "Session activation failed. Required CREATING state");
        sessionStorage.setSessionStatus(_sessionId, SessionLib.Status.ACTIVE);
    }

    // @formatter:off
    function closeSession(
        address _application,
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        external
    // @formatter:on
    {
        //TODO:implementation: check if msg.sender is owner of the session
        ASessionStorage sessionStorage = ASessionStorage(context.get(STORAGE));
        SessionLib.Status status = sessionStorage.retrieveSessionStatus(_sessionId);
        require(status == SessionLib.Status.ACTIVE, "session is not ACTIVE");
        sessionStorage.createSessionClose(_sessionId, _success, _fail);
        sessionStorage.setSessionStatus(_sessionId, SessionLib.Status.CLOSE_REQUESTED);
        ASessionOracle(context.get(ORACLE)).onNextCloseSession(_sessionId);
    }

    function confirmClose(uint256 _sessionId) public {
        //TODO:implementation: check if msg.sender is ORACLE
        ASessionStorage sessionStorage = ASessionStorage(context.get(STORAGE));
        SessionLib.Status status = sessionStorage.retrieveSessionStatus(_sessionId);
        require(status == SessionLib.Status.CLOSE_REQUESTED, "Session confirm close failed. Required CLOSE_REQUESTED state.");
        sessionStorage.setSessionStatus(_sessionId, SessionLib.Status.CLOSED);
        (function(uint256) external callback,) = sessionStorage.retrieveSessionClose(_sessionId);
        ASessionController(context.get(CONTROLLER)).respondClose(_sessionId, callback);
    }

    function confirmFailClose(uint256 _sessionId) public {
        ASessionStorage sessionStorage = ASessionStorage(context.get(STORAGE));
        SessionLib.Status status = sessionStorage.retrieveSessionStatus(_sessionId);
        require(status == SessionLib.Status.CLOSE_REQUESTED, "fail to confirm fail session close. session is not CLOSE_REQUESTED.");
        (,function(uint256) external callback) = sessionStorage.retrieveSessionClose(_sessionId);
        ASessionController(context.get(CONTROLLER)).respondFailClose(_sessionId, callback);
    }

}