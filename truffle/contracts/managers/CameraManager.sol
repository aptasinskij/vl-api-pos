pragma solidity 0.4.24;

import {CameraLib, SessionLib} from "../libs/Libraries.sol";
import {ACameraManager} from "./Managers.sol";
import {ACameraOracle} from "../oracles/Oracles.sol";
import {ACameraController} from "../controllers/Controllers.sol";
import "../Platform.sol";

contract CameraManager is ACameraManager, Mortal, Named("camera-manager"), Component {

    using CameraLib for address;
    using SessionLib for address;

    string constant ORACLE = "camera-oracle";
    string constant CONTROLLER = "camera-controller";

    constructor(address _config) Component(_config) public {}

    function scanQRCode(
        address _application,
        uint256 _sessionId,
        bool _lights,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )
        public 
        returns (bool _accepted) 
    {
        address sessionOwner = database.retrieveSessionApplicationDeployedAddress(_sessionId);
        require(sessionOwner == _application, "illegal session access");
        require(database.sessionIsActive(_sessionId), "session is not active");
        uint256 startQRScanId = database.getNextStartQRScanId();
        database.createStartQRScan(CameraLib.StartQRScan({
            id : startQRScanId,
            sessionId : _sessionId,
            lights : _lights,
            scanned : _scanned,
            success : _success,
            fail : _fail
            })
        );
        _accepted = ACameraOracle(context.get(ORACLE)).onNextStartQRScan(startQRScanId);
    }

    function confirmFailStart(uint256 _commandId) public {
        //here can be some logic
        CameraLib.StartQRScan memory command = database.retrieveStartQRScan(_commandId);
        ACameraController(context.get(CONTROLLER)).respondFailStart(command.sessionId, command.fail);
    }

    function confirmStart(uint256 _commandId, string memory _port, string memory _url, string memory _href) public {
        //here can be some logic
        CameraLib.StartQRScan memory command = database.retrieveStartQRScan(_commandId);
        ACameraController(context.get(CONTROLLER)).respondStart(_commandId, _port, _url, _href, command.success);
    }

    function confirmScanned(uint256 _sessionId, string memory _qr) public {
        //here can be some logic
        CameraLib.StartQRScan memory command = database.retrieveStartQRScanBySessionId(_sessionId);
        ACameraController(context.get(CONTROLLER)).respondScanned(_sessionId, _qr, command.scanned);
    }

    function stopQRScanning(
        address _application,
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )   // @formatter:off
        public
        returns (bool _accepted)
        // @formatter:on
    {
        address sessionOwner = database.retrieveSessionApplicationDeployedAddress(_sessionId);
        require(sessionOwner == _application, "illegal session access");
        require(database.sessionIsActive(_sessionId), "session is not active");
        uint256 stopQRScanId = database.getNextStopQRScanId();
        database.createStopQRScan(CameraLib.StopQRScan({
            id : stopQRScanId,
            sessionId : _sessionId,
            success : _success,
            fail : _fail
            }));
        _accepted = ACameraOracle(context.get(ORACLE)).onNextStopQRScan(stopQRScanId);
    }

    function confirmStop(uint256 _commandId) public {
        //here can be some logic
        CameraLib.StopQRScan memory command = database.retrieveStopQRScan(_commandId);
        ACameraController(context.get(CONTROLLER)).respondStop(command.sessionId, command.success);
    }

    function confirmFailStop(uint256 _commandId) public {
        //here can be some logic
        CameraLib.StopQRScan memory command = database.retrieveStopQRScan(_commandId);
        ACameraController(context.get(CONTROLLER)).respondFailStop(command.sessionId, command.fail);
    }

}