pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/ACameraController.sol";
import "../managers/api/ACameraManager.sol";

contract CameraController is ACameraController, Named("camera-controller"), Mortal, Component {

    constructor(address _config) Component(_config) public {}

    function scanQRCodeWithLights(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )   // @formatter:off
        external
        // @formatter:on
    {
        ACameraManager(context.get("camera-manager")).scanQRCode(msg.sender, _sessionId, true, _success, _scanned, _fail);
    }

    function scanQRCodeWithoutLights(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )   // @formatter:off
        external
        // @formatter:on
    {
        ACameraManager(context.get("camera-manager")).scanQRCode(msg.sender, _sessionId, false, _success, _scanned, _fail);
    }

    function respondFailStart(uint256 _sessionId, function(uint256) external _callback) public {
        _callback(_sessionId);
    }

    function respondStart(
        uint256 _sessionId,
        string _port,
        string _url,
        string _href,
        function(uint, string memory, string memory, string memory) external _callback
    )   // @formatter:off
        public
        // @formatter:on
    {
        _callback(_sessionId, _port, _url, _href);
    }

    function respondScanned(
        uint256 _sessionId,
        string _qr,
        function(uint256, string memory) external _callback
    )
        public
    {
        _callback(_sessionId, _qr);
    }

    function stopQRScanning(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )   // @formatter:off
        external
        // @formatter:on
    {
        ACameraManager(context.get("camera-manager")).stopQRScanning(msg.sender, _sessionId, _success, _fail);
    }

    function respondStop(uint256 _sessionId, function(uint256) external _callback) public {
        _callback(_sessionId);
    }

    function respondFailStop(uint256 _sessionId, function(uint256) external _callback) public {
        _callback(_sessionId);
    }

}