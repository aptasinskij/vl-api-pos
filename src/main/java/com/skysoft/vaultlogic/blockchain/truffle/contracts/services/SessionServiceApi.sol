pragma solidity 0.4.24;

interface SessionServiceApi {
    
    function createSession(uint256 id, uint256 appId, string xToken) external;

    function closeSession(uint256 sessionId) external;

}