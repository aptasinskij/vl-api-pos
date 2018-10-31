pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "./api/ACameraStorage.sol";
import "../platform/Component.sol";
import "./libs/CameraLib.sol";

contract CameraStorage is ACameraStorage, Named("camera-storage"), Mortal, Component {

    using CameraLib for CameraLib.Start;
    using CameraLib for CameraLib.Stop;

    CameraLib.Start[] private starts;

    mapping(uint256 => uint256) sessionToStart;

    CameraLib.Stop[] private stops;

    constructor(address _config) Component(_config) public {}

    // @formatter:off
    function saveStart(
        uint256 _sessionId,
        bool _enableLight,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )
        public
        returns (
            uint256 _id
        )
    // @formatter:on
    {
        _id = starts.push(CameraLib.Start(_sessionId, _enableLight, _success, _scanned, _fail)) - 1;
        sessionToStart[_sessionId] = _id;
    }

    function retrieveStart(
        uint256 _id
    )
        public
        view
        returns (
            uint256 _sessionId,
            bool _enableLight,
            function(uint256, string memory, string memory, string memory) external _success,
            function(uint256, string memory) external _scanned,
            function(uint256) external _fail
        )
    // @formatter:on
    {
        _sessionId = starts[_id].sessionId;
        _enableLight = starts[_id].enableLight;
        _success = starts[_id].success;
        _scanned = starts[_id].scanned;
        _fail = starts[_id].fail;
    }

    function retrieveStartBySessionId(
        uint256 _id
    )
        public
        view
        returns (
            uint256 _sessionId,
            bool _enableLight,
            function(uint256, string memory, string memory, string memory) external _success,
            function(uint256, string memory) external _scanned,
            function(uint256) external _fail
        )
    // @formatter:on
    {
        (_sessionId, _enableLight, _success, _scanned, _fail) = retrieveStart(sessionToStart[_id]);
    }

    // @formatter:off
    function saveStop(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        public
        returns (
            uint256 _id
        )
    // @formatter:on
    {
        _id = stops.push(CameraLib.Stop(_sessionId, _success, _fail)) - 1;
    }

    // @formatter:off
    function retrieveStop(
        uint256 _id
    )
        public
        view
        returns (
            uint256 _sessionId,
            function(uint256) external _success,
            function(uint256) external _fail
        )
    // @formatter:on
    {
        _sessionId = stops[_id].sessionId;
        _success = stops[_id].success;
        _fail = stops[_id].fail;
    }

}
