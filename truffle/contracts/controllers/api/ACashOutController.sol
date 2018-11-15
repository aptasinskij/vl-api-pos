pragma solidity 0.4.24;

import "./public/CashOutApi.sol";

contract ACashOutController is CashOutApi {

    // @formatter:off
    function respondOpened(
        string _kioskId,
        uint256 _cashOutId,
        function(string memory, uint256) external _callback
    )
        public;
    // @formatter:on

    // @formatter:off
    function respondFailOpen(
        string _kioskId,
        function(string memory) external _callback
    )
        public;
    // @formatter:on

    // @formatter:off
    function respondValidated(
        uint256 _sessionId,
        uint256 _cashOutId,
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
    function respondRolledBack(
        uint256 _cashOutId,
        function(uint256) external _callback
    )
        public;
    // @formatter:on

}
