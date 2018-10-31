pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "./api/ACameraOracle.sol";
import "../platform/Component.sol";
import "../managers/api/ACameraManager.sol";

contract CameraOracle is ACameraOracle, Named("camera-oracle"), Mortal, Component {

    string constant MANAGER = "camera-manager";

    constructor(address _config) Component(_config) public {}

    function onNextStartQRScan(uint256 _commandId, uint256 _sessionId, bool _lights) public {
        emit StartScanQR(_commandId, _sessionId, _lights);
    }

    function successStart(uint256 _commandId, string _port, string _url, string _href) public {
        ACameraManager(context.get(MANAGER)).confirmStart(_commandId, _port, _url, _href);
    }

    function failStart(uint256 _commandId) public {
        ACameraManager(context.get(MANAGER)).confirmFailStart(_commandId);
    }

    function scanned(uint256 _sessionId, string _qr) public {
        ACameraManager(context.get(MANAGER)).confirmScanned(_sessionId, _qr);
    }

    function onNextStopQRScan(uint256 _commandId, uint256 _sessionId) public {
        emit StopScanQR(_commandId, _sessionId);
    }

    function successStop(uint256 _commandId) public {
        ACameraManager(context.get(MANAGER)).confirmStop(_commandId);
    }

    function failStop(uint256 _commandId) public {
        ACameraManager(context.get(MANAGER)).confirmFailStop(_commandId);
    }

}
