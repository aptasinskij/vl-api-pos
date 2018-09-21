pragma solidity 0.4.24;

interface ISessionStorage {
    
    function getAppId(uint256 index) external view returns (uint256);

    function getXToken(uint256 index) external view returns(string xToken);

    function getAppIdAndXToken(uint256 index) external view returns(uint256 applicationId, string xToken);

    function save(uint256 sessionId, uint256 appId, string xToken, uint256 status) external;
    
    function getSession(uint256 index) external view returns(uint256 appId, string xToken, uint256 status);

    function setHasActiveCashIn(uint256 _sessionId, bool _flag) external;

    function isHasActiveCashIn(uint256 _sessionId) external view returns(bool);
    
    function getStatus(uint256 index) external view returns (uint256);

    function getStatusAndXToken(uint256 index) external view returns (uint256, string);

    function setStatus(uint256 index, uint256 status) external;
                
}