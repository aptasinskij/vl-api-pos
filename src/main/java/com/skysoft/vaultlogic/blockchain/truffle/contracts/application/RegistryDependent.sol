pragma solidity 0.4.24;

import "../registry/IRegistry.sol";

contract RegistryDependent {

    address private registry;

    constructor(address regAddr) public {
        registry = regAddr;
    }

    function componentForName(string name) internal view returns(address componentAddress) {
        componentAddress = IRegistry(registry).get(name);
        require(componentAddress != 0x0, "Registry returned 0x0");
    }

}