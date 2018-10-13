pragma solidity 0.4.24;

import {ACameraOracle} from "./Oracles.sol";
import {CameraLib} from "../libs/Libraries.sol";
import {ACameraManager} from "../managers/Managers.sol";
import "../Platform.sol";

contract CameraOracle is ACameraOracle, Mortal, Named("camera-oracle"), Component {

    using CameraLib for address;

    string constant CAMERA_MANAGER = "camera-manager";

    constructor(address _config) Component(_config) public {}

    function onNextStartQRScan(uint256 _commandId) public returns (bool _accepted) {
        CameraLib.StartQRScan memory command = database.retrieveStartQRScan(_commandId);
        emit StartScanQR(command.id, command.sessionId, command.lights);
        _accepted = true;
    }

    function successStart(uint256 _commandId) public {
        CameraLib.StartQRScan memory command = database.retrieveStartQRScan(_commandId);
        ACameraManager(context.get(CAMERA_MANAGER)).confirmStart(command.sessionId, command.success);
    }

    function qrScanned(uint256 _sessionId, string memory _port, string memory _url, string memory _href) public {
        CameraLib.StartQRScan memory command = database.retrieveStartQRScanBySessionId(_sessionId);
        ACameraManager(context.get(CAMERA_MANAGER)).confirmScanned(command.sessionId, _port, _url, _href, command.scanned);
    }

    function failQRScan(uint256 _commandId) public {
        CameraLib.StartQRScan memory command = database.retrieveStartQRScan(_commandId);
        ACameraManager(context.get(CAMERA_MANAGER)).confirmFailStart(command.sessionId, command.fail);
    }

    function onNextStopQRScan(uint256 _commandId) public returns (bool _accepted) {
        CameraLib.StopQRScan memory command = database.retrieveStopQRScan(_commandId);
        emit StopScanQR(command.id, command.sessionId);
        _accepted = true;
    }

    function successStopQRScan(uint256 _commandId) public {
        CameraLib.StopQRScan memory command = database.retrieveStopQRScan(_commandId);
        ACameraManager(context.get(CAMERA_MANAGER)).confirmStop(command.sessionId, command.success);
    }

    function failStopQRScan(uint256 _commandId) public {
        CameraLib.StopQRScan memory command = database.retrieveStopQRScan(_commandId);
        ACameraManager(context.get(CAMERA_MANAGER)).confirmFailStop(command.sessionId, command.fail);
    }

}
