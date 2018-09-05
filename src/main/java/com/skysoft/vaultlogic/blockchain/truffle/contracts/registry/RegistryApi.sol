pragma solidity 0.4.24;

interface RegistryApi {
    
    function get(string name) external view returns(address);

    function register(string name, address addr) external;

}