pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/ACameraController.sol";
import "../managers/ACameraManager.sol";

contract CameraController is ACameraController, Named("camera-controller"), Mortal, Component {

    /*using ApplicationLib for address;*/

    string constant MANAGER = "camera-manager";
/*
    modifier isRegistered {
        require(database.isRegistered(msg.sender), "only registered allowed");
        _;
    }*/

    modifier onlyManager {
        require(msg.sender == context.get(MANAGER), "only manager allowed");
        _;
    }

    constructor(address _config) Component(_config) public {}

    function scanQRCodeWithLights(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )   // @formatter:off
        public
        /*isRegistered*/
        returns (bool _accepted)
        // @formatter:on
    {
        return ACameraManager(context.get(MANAGER)).scanQRCode(msg.sender, _sessionId, true, _success, _scanned, _fail);
    }

    function scanQRCode(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )   // @formatter:off
        public
        /*isRegistered*/
        returns (bool _accepted)
        // @formatter:on
    {
        return ACameraManager(context.get(MANAGER)).scanQRCode(msg.sender, _sessionId, false, _success, _scanned, _fail);
    }

    function respondFailStart(uint256 _sessionId, function(uint256) external _callback) public onlyManager {
        _callback(_sessionId);
    }

    function respondStart(
        uint256 _sessionId,
        string memory _port,
        string memory _url,
        string memory _href,
        function(uint, string memory, string memory, string memory) external _callback
    )   // @formatter:off
        public
        onlyManager
        // @formatter:on
    {
        _callback(_sessionId, _port, _url, _href);
    }

    function respondScanned(
        uint256 _sessionId,
        string memory _qr,
        function(uint256, string memory) external _callback
    )
        public
        onlyManager
    {
        _callback(_sessionId, _qr);
    }

    function stopQRScanning(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )   // @formatter:off
        public
        /*isRegistered*/
        returns (bool _accepted)
        // @formatter:on
    {
        return ACameraManager(context.get(MANAGER)).stopQRScanning(msg.sender, _sessionId, _success, _fail);
    }

    function respondStop(uint256 _sessionId, function(uint256) external _callback) public onlyManager {
        _callback(_sessionId);
    }

    function respondFailStop(uint256 _sessionId, function(uint256) external _callback) public onlyManager {
        _callback(_sessionId);
    }

}