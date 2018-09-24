pragma solidity 0.4.24;

contract ASessionManager {
    
    function createSession(uint256 sessionId, uint256 appId, string xToken) public;

    function closeSession(uint256 sessionId) public;

    function confirmClose(uint256 sessionId) public;

    function isActive(uint256 sessionId) public view returns(bool);

    function isHasActiveCashIn(uint256 _sessionId) public view returns(bool);

    function activate(uint256 _sessionId) public returns(bool);

}