pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/ASessionController.sol";
import "../managers/api/ASessionManager.sol";

contract SessionController is ASessionController, Named("session-controller"), Mortal, Component {

    string constant MANAGER = "session-manager";

    constructor(address _config) Component(_config) public {}

    // formatter:off
    function closeSession(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        external
    {
        ASessionManager(context.get(MANAGER)).closeSession(msg.sender, _sessionId, _success, _fail);
    }
    // formatter:on

    // formatter:off
    function respondClose(
        uint256 _sessionId,
        function(uint256) external _callback
    )
        public
    // formatter:on
    {
        _callback(_sessionId);
    }

    // formatter:off
    function respondFailClose(
        uint256 _sessionId,
        function(uint256) external _callback
    )
        public
    // formatter:on
    {
        _callback(_sessionId);
    }

}
