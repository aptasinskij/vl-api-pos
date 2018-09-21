pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../repositories/parameter/IParameterStorage.sol";

contract ParameterManager is RegistryComponent, IParameterManager {

    string constant COMPONENT_NAME = "parameter-manager";

    constructor(address registryAddress) RegistryComponent(registryAddress) public {}

    function setVLFee(address customer, uint256 percent) external {
        require(percent >= 0, "The fee percent for vault logic cannot be less then zero");
        _parameterStorage().setVLFee(customer, percent);
    }

    function getVLFee(address consumer) external view returns (uint256) {
        return _parameterStorage().getVLFee(consumer);
    }

}