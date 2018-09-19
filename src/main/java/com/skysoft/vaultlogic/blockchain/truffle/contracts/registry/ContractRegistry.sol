pragma solidity 0.4.24;

import "./IRegistry.sol";

contract ContractRegistry is IRegistry {

    event ComponentRegistered(string name, address addr, uint256 index);
    event ComponentAddressUpdated(string name, address addr);

    mapping(string => address) contracts;
    mapping(string => bool) enabled;
    mapping(string => uint256) indexOfName;

    uint256 public numberOfComponents;

    string[] public names;

    function get(string name) external view returns(address) {
        if (enabled[name]) return contracts[name];
        return 0x0;
    }

    function register(string name, address addr) external {
        if (contracts[name] == 0x0) {
            uint256 index = names.push(name) - 1;
            indexOfName[name] = index;
            enabled[name] = true;
            numberOfComponents++;
            emit ComponentRegistered(name, addr, index);
        } else {
            emit ComponentAddressUpdated(name, addr);
        }
        contracts[name] = addr;
    }

    function disable(string name) public {
        enabled[name] = false;
    }

    function enable(string name) public {
        enabled[name] = true;
    }

    function getState(string name) public view returns(bool) {
        return enabled[name];
    }
    
}