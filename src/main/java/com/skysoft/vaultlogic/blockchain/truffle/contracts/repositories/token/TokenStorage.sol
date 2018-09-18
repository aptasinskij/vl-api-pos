pragma solidity 0.4.24;

import "./TokenLib.sol";
import "../../registry/RegistryComponent.sol";

contract TokenStorage is RegistryComponent {

    string constant COMPONENT_NAME = "token-storage";
    string constant DATABASE = "database";

    using TokenLib for address;

    constructor(address registryAddress) RegistryComponent(registryAddress) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function set(address customer, uint256 amount) public {
        return lookup(DATABASE).set(customer, amount);
    }

    function get(address consumer) public view returns (uint256) {
        return lookup(DATABASE).get(consumer);
    }

}