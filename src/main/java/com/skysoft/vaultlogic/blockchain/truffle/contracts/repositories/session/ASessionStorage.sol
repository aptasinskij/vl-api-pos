pragma solidity 0.4.24;

contract ASessionStorage {

    event Saved(uint256 sessionId, uint256 appId, string xToken, uint256 status);
    event StatusUpdated(uint256 index, uint256 status);
    event ActiveCashIn(uint256 _sessionId, bool _flag);
    
    function getAppId(uint256 index) public view returns (uint256);

    function getXToken(uint256 index) public view returns(string xToken);

    function getAppIdAndXToken(uint256 index) public view returns(uint256 applicationId, string xToken);

    function save(uint256 sessionId, uint256 appId, string xToken, uint256 status) public;
    
    function getSession(uint256 index) public view returns(uint256 appId, string xToken, uint256 status);

    function setHasActiveCashIn(uint256 _sessionId, bool _flag) public;

    function isHasActiveCashIn(uint256 _sessionId) public view returns(bool);
    
    function getStatus(uint256 index) public view returns (uint256);

    function getStatusAndXToken(uint256 index) public view returns (uint256, string);

    function setStatus(uint256 index, uint256 status) public;
                
}