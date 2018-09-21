pragma solidity 0.4.24;

import "../../registry/RegistryComponent.sol";
import "./ParameterLib.sol";

contract ParameterStorage is RegistryComponent {

    string constant COMPONENT_NAME = "parameter-storage";
    string constant DATABASE = "database";

    using ParameterLib for address;

    constructor(address registryAddress) RegistryComponent(registryAddress) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function setVLFee(address customer, uint256 percent) public {
        lookup(DATABASE).setVLFee(customer, percent);
    }

    function getVLFee(address consumer) public view returns (uint256) {
        return lookup(DATABASE).getVLFee(consumer);
    }

}
