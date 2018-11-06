pragma solidity 0.4.24;

contract ASessionManager {

    function createSession(uint256 _sessionId, uint256 _appId, string _xToken, string _kioskId) public;

    function activate(uint256 _sessionId) public;

    // @formatter:off
    function closeSession(
        address _application,
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        external;
    // @formatter:on

    function confirmClose(uint256 _sessionId) public;

    function confirmFailClose(uint256 _sessionId) public;

}
