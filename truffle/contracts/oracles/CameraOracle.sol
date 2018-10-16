pragma solidity 0.4.24;

import {ACameraOracle} from "./Oracles.sol";
import {CameraLib} from "../libs/Libraries.sol";
import {ACameraManager} from "../managers/Managers.sol";
import "../Platform.sol";

contract CameraOracle is ACameraOracle, Mortal, Named("camera-oracle"), Component {

    using CameraLib for address;

    string constant MANAGER = "camera-manager";

    constructor(address _config) Component(_config) public {}

    function onNextStartQRScan(uint256 _commandId) public returns (bool _accepted) {
        (uint256 sessionId, bool lights) = database.retrieveStartQRScanSessionIdLights(_commandId);
        emit StartScanQR(_commandId, sessionId, lights);
        _accepted = true;
    }

    function successStart(uint256 _commandId, string memory _port, string memory _url, string memory _href) public {
        ACameraManager(context.get(MANAGER)).confirmStart(_commandId, _port, _url, _href);
    }

    function failStart(uint256 _commandId) public {
        ACameraManager(context.get(MANAGER)).confirmFailStart(_commandId);
    }

    function scanned(uint256 _sessionId, string memory _qr) public {
        ACameraManager(context.get(MANAGER)).confirmScanned(_sessionId, _qr);
    }

    function onNextStopQRScan(uint256 _commandId) public returns (bool _accepted) {
        uint256 sessionId = database.retrieveStopQRScanSessionId(_commandId);
        emit StopScanQR(_commandId, sessionId);
        _accepted = true;
    }

    function successStop(uint256 _commandId) public {
        ACameraManager(context.get(MANAGER)).confirmStop(_commandId);
    }

    function failStop(uint256 _commandId) public {
        ACameraManager(context.get(MANAGER)).confirmFailStop(_commandId);
    }

}
