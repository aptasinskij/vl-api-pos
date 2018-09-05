pragma solidity 0.4.24;

contract ContractRegistry {
    
    mapping(string => address) contracts;
    
    function get(string name) external view returns(address) {
        return contracts[name];
    }

    function register(string name, address addr) external {
        contracts[name] = addr;
    }
    
}