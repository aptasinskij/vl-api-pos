pragma solidity 0.4.24;

interface SessionApi {

    // formatter:off
    function closeSession(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        external;
    // formatter:on

}
