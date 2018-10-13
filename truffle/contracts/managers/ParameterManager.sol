pragma solidity 0.4.24;

import "../registry/Component.sol";
import {AParameterManager} from "./Managers.sol";
import {AParameterStorage} from "../storages/Storages.sol";
import {Named} from "../Platform.sol";

contract ParameterManager is Component, AParameterManager, Named("parameter-manager") {

    string constant COMPONENT_NAME = "parameter-manager";

    constructor(address registryAddress) Component(registryAddress) public {}

    function getName() internal pure returns (string) {
        return COMPONENT_NAME;
    }

    function setVLFee(uint256 percent) public {
        require(percent >= 0, "The fee percent for vault logic cannot be less then zero");
        AParameterStorage(_parameterStorage()).setVLFee(percent);
    }

    function getVLFee() public view returns (uint256) {
        return AParameterStorage(_parameterStorage()).getVLFee();
    }

}