pragma solidity 0.4.24;

import "./public/SessionApi.sol";

contract ASessionController is SessionApi {

    // formatter:off
    function respondClose(
        uint256 _sessionId,
        function(uint256) external _callback
    )
        public;
    // formatter:on

    // formatter:off
    function respondFailClose(
        uint256 _sessionId,
        function(uint256) external _callback
    )
        public;
    // formatter:on

}
