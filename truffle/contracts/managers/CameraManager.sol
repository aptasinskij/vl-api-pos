pragma solidity 0.4.24;

import "../storages/ACameraStorage.sol";
import "../platform/Mortal.sol";
import "../platform/Named.sol";
import "../platform/Component.sol";
import "../controllers/api/ACameraController.sol";
import "../oracles/api/ACameraOracle.sol";
import "./api/ACameraManager.sol";

contract CameraManager is ACameraManager, Named("camera-manager"), Mortal, Component {

    string constant STORAGE = "camera-storage";
    string constant ORACLE = "camera-oracle";
    string constant CONTROLLER = "camera-controller";

    constructor(address _config) Component(_config) public {}

    // @formatter:off
    function scanQRCode(
        address _application,
        uint256 _sessionId,
        bool _lights,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )
        public 
        returns (
            bool _accepted
        )
    // @formatter:on
    {
        uint256 id = ACameraStorage(context.get(STORAGE)).saveStart(_sessionId, _lights, _success, _scanned, _fail);
        _accepted = ACameraOracle(context.get(ORACLE)).onNextStartQRScan(id);
    }

    function confirmFailStart(uint256 _commandId) public {
        function(uint256) external callback;
        uint256 sessionId;
        (sessionId,,,,callback) = ACameraStorage(context.get(STORAGE)).retrieveStart(_commandId);
        ACameraController(context.get(CONTROLLER)).respondFailStart(sessionId, callback);
    }

    function confirmStart(uint256 _commandId, string memory _port, string memory _url, string memory _href) public {
        function(uint256, string memory, string memory, string memory) external callback;
        uint256 sessionId;
        (sessionId,,callback,,) = ACameraStorage(context.get(STORAGE)).retrieveStart(_commandId);
        ACameraController(context.get(CONTROLLER)).respondStart(sessionId, _port, _url, _href, callback);
    }

    function confirmScanned(uint256 _sessionId, string memory _qr) public {
        function(uint256, string memory) external callback;
        uint256 sessionId;
        (sessionId,,,callback,) = ACameraStorage(context.get(STORAGE)).retrieveStartBySessionId(_sessionId);
        ACameraController(context.get(CONTROLLER)).respondScanned(sessionId, _qr, callback);
    }

    // @formatter:off
    function stopQRScanning(
        address _application,
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        public
        returns (
            bool _accepted
    )
    // @formatter:on
    {
        uint256 id = ACameraStorage(context.get(STORAGE)).saveStop(_sessionId, _success, _fail);
        _accepted = ACameraOracle(context.get(ORACLE)).onNextStopQRScan(id);
    }

    function confirmStop(uint256 _commandId) public {
        function(uint256) external callback;
        uint256 sessionId;
        (sessionId,callback,) = ACameraStorage(context.get(STORAGE)).retrieveStop(_commandId);
        ACameraController(context.get(CONTROLLER)).respondStop(sessionId, callback);
    }

    function confirmFailStop(uint256 _commandId) public {
        function(uint256) external callback;
        uint256 sessionId;
        (sessionId,,callback) = ACameraStorage(context.get(STORAGE)).retrieveStop(_commandId);
        ACameraController(context.get(CONTROLLER)).respondFailStop(sessionId, callback);
    }

}