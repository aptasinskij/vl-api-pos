pragma solidity 0.4.24;

interface ApplicationOracleApi {
    
    function register(uint256 index, string name, address owner, string url, address appAddr, uint256 status) external;

}