pragma solidity 0.4.24;

interface ApplicationServiceApi {
    
    function registerApplication(uint256 appId, string name, address owner, string url, address appAddr) external;

    function enableApplication(uint256 appId) external;

}