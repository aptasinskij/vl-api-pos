pragma solidity 0.4.24;

interface SessionApi {a

    /**
    @notice Close Session
    @dev
    @param _sessionId: id of the session which session must close
    @param _success: callback function(_sessionId) for informing about successful case of closing session
    @param _fail: callback function(_sessionId) for informing about failed case of closing session
    @return {
    }
    */
    // formatter:off
    function closeSession(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        external;
    // formatter:on

}
