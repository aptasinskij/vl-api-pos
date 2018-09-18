pragma solidity 0.4.24;

interface ISessionManager {
    
    function createSession(uint256 sessionId, uint256 appId, string xToken) external;

    function closeSession(uint256 sessionId) external;

    function confirmClose(uint256 sessionId) external;

    function isActive(uint256 sessionId) external view returns(bool);

    function activate(uint256 _sessionId) external returns(bool);

}