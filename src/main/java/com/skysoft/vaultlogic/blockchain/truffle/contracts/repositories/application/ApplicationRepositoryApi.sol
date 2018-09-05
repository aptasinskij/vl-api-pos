pragma solidity 0.4.24;

interface ApplicationRepositoryApi {
    
    function save(uint256 appId, string name, address owner, string url, address appAddr, uint256 status) external;

    function get(uint256 appId) external view returns (string, address, string, address, uint256);

    function getApplicationName(uint256 appId) external view returns(string);

    function getApplicationOwner(uint256 appId) external view returns(address);

    function getApplicationUrl(uint256 appId) external view returns(string);

    function setApplicationUrl(uint256 appId, string url) external;

    function getApplicationAddress(uint256 appId) external view returns(address);

    function setApplicationAddress(uint256 appId, address appAddr) external;

    function getApplicationStatus(uint256 appId) external view returns(uint256);

    function setApplicationStatus(uint256 appId, uint256 status) external;

}