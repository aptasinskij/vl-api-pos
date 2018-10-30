pragma solidity 0.4.24;

import "./public/CameraApi.sol";

contract ACameraController is CameraApi {

    // @formatter:off
    function respondStart(
        uint256 _sessionId,
        string memory _port,
        string memory _url,
        string memory _href,
        function(uint, string memory, string memory, string memory) external _callback
    )
        public;
    // @formatter:on

    // @formatter:off
    function respondScanned(
        uint256 _sessionId,
        string memory _qr,
        function(uint256, string memory) external _callback
    )
        public;
    // @formatter:on

    // @formatter:off
    function respondFailStart(
        uint256 _sessionId,
        function(uint256) external _callback
    )
        public;
    // @formatter:on

    // @formatter:off
    function respondStop(
        uint256 _sessionId,
        function(uint256) external _callback
    )
        public;
    // @formatter:on

    // @formatter:off
    function respondFailStop(
        uint256 _sessionId,
        function(uint256) external _callback
    )
        public;
    // @formatter:on

}
