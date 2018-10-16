pragma solidity 0.4.24;

import {Context} from "../Platform.sol";

contract RegistryDependent {

    address private registry;

    constructor(address regAddr) public {
        registry = regAddr;
    }

    function componentForName(string memory name) internal view returns(address componentAddress) {
        componentAddress = Context(registry).get(name);
        require(componentAddress != 0x0, "Registry returned 0x0");
    }

}