pragma solidity 0.4.24;

import {CameraLib} from "../libs/Libraries.sol";
import {ACameraManager} from "./Managers.sol";
import {ACameraOracle} from "../oracles/Oracles.sol";
import "../registry/Component.sol";

contract CameraManager is ACameraManager, Component {

    using CameraLib for address;

    string constant COMPONENT_NAME = "camera-manager";

    constructor(address registry) Component(registry) public {}

    function getName() internal pure returns (string name) {
        return COMPONENT_NAME;
    }

    function scanQRCodeWithLights(
        address application,
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _scanned,
        function(uint256) external _success,
        function(uint256) external _fail
    )   // @formatter:off
        public
        returns (bool _accepted)
    {
        uint256 startQRScanId = _database().getNextStartQRScanId();
        _database().createStartQRScan(
            CameraLib.StartQRScan({
                id : startQRScanId,
                sessionId : _sessionId,
                lights : true,
                scanned : _scanned,
                success : _success,
                fail : _fail
            })
        );
        // @formatter:on
        _accepted = ACameraOracle(_cameraOracle()).onNextStartQRScan(startQRScanId);
    }

    function scanQRCode(
        address application,
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _scanned,
        function(uint256) external _success,
        function(uint256) external _fail
    )   // @formatter:off
        public
        returns (bool _accepted)
    {
        uint256 startQRScanId = _database().getNextStartQRScanId();
        _database().createStartQRScan(
            CameraLib.StartQRScan({
            id : startQRScanId,
            sessionId : _sessionId,
            lights : false,
            scanned : _scanned,
            success : _success,
            fail : _fail
            })
        );
        // @formatter:on
        _accepted = ACameraOracle(_cameraOracle()).onNextStartQRScan(startQRScanId);
    }

    function confirmStart(uint256 _sessionId, function(uint256) external _callback) {
        _callback(_sessionId);
    }

    function confirmFailStart(uint256 _sessionId, function(uint256) external _callback) {
        _callback(_sessionId);
    }

    function confirmScanned(
        uint256 _sessionId,
        string memory _port,
        string memory _url,
        string memory _href,
        function(uint256, string memory, string memory, string memory) external _callback
    ) public {
        _callback(_sessionId, _port, _url, _href);
    }

    function stopQRScanning(
        address application,
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )   // @formatter:off
        public
        returns (bool _accepted)
    {
        uint256 stopQRScanId = _database().getNextStopQRScanId();
        _database().createStopQRScan(CameraLib.StopQRScan({
            id: stopQRScanId,
            sessionId: _sessionId,
            success: _success,
            fail: _fail
        }));
        // @formatter:on
        _accepted = ACameraOracle(_cameraOracle()).onNextStopQRScan(stopQRScanId);
    }

    function confirmStop(uint256 _sessionId, function(uint256) external _callback) public {
        _callback(_sessionId);
    }

    function confirmFailStop(uint256 _sessionId, function(uint256) external _callback) public {
        _callback(_sessionId);
    }

}