pragma solidity 0.4.24;

import "./public/CashOutApi.sol";

contract ACashOutController is CashOutApi {

    // @formatter:off
    function respondOpened(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _callback
    )
        public;
    // @formatter:on

    // @formatter:off
    function respondFailOpen(
        uint256 _sessionId,
        function(uint256, uint256) external _callback
    )
        public;
    // @formatter:on

    // @formatter:off
    function respondClosed(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _callback
    )
        public;
    // @formatter:on

    // @formatter:off
    function respondFailClose(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _callback
    )
        public;
    // @formatter:on
}
