pragma solidity 0.4.24;

import {ACameraOracle} from "./Oracles.sol";
import {CameraLib} from "../libs/Libraries.sol";
import {ACameraManager} from "../managers/Managers.sol";

contract CameraOracle is ACameraOracle {

    using CameraLib for address;

    string constant COMPONENT_NAME = "camera-oracle";

    constructor(address registry) ACameraOracle(registry) public {}

    function getName() internal pure returns (string name) {
        return COMPONENT_NAME;
    }

    function onNextStartQRScan(uint256 _commandId) public returns (bool _accepted) {
        CameraLib.StartQRScan memory command = _database().retrieveStartQRScan(_commandId);
        emit StartScanQR(command.id, command.sessionId, command.lights);
    }

    function successStart(uint256 _commandId) public {
        CameraLib.StartQRScan memory command = _database().retrieveStartQRScan(_commandId);
        ACameraManager(_cameraManager()).confirmStart(command.sessionId, command.success);
    }

    function qrScanned(uint256 _sessionId, string memory _port, string memory _url, string memory _href) public {
        CameraLib.StartQRScan memory command = _database().retrieveStartQRScanBySessionId(_sessionId);
        ACameraManager(_cameraManager()).confirmScanned(command.sessionId, _port, _url, _href, command.scanned);
    }

    function failQRScan(uint256 _commandId) public {
        CameraLib.StartQRScan memory command = _database().retrieveStartQRScan(_commandId);
        ACameraManager(_cameraManager()).confirmFailStart(command.sessionId, command.fail);
    }

    function onNextStopQRScan(uint256 _commandId) public returns (bool _accepted) {
        CameraLib.StopQRScan memory command = _database().retrieveStopQRScan(_commandId);
        emit StopScanQR(command.id, command.sessionId);
    }

    function successStopQRScan(uint256 _commandId) public {
        CameraLib.StopQRScan memory command = _database().retrieveStopQRScan(_commandId);
        ACameraManager(_cameraManager()).confirmStop(command.sessionId, command.success);
    }

    function failStopQRScan(uint256 _commandId) public {
        CameraLib.StopQRScan memory command = _database().retrieveStopQRScan(_commandId);
        ACameraManager(_cameraManager()).confirmFailStop(command.sessionId, command.fail);
    }

}
