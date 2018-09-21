pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../repositories/parameter/IParameterStorage.sol";

contract ParameterManager is RegistryComponent, IParameterManager {

    string constant COMPONENT_NAME = "parameter-manager";

    constructor(address registryAddress) RegistryComponent(registryAddress) public {}

    function setVLFee(uint256 percent) public {
        require(percent >= 0, "The fee percent for vault logic cannot be less then zero");
        _parameterStorage().setVLFee(percent);
    }

    function getVLFee() public view returns (uint256) {
        return _parameterStorage().getVLFee();
    }

}