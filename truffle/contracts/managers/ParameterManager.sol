pragma solidity 0.4.24;

import {AParameterManager} from "./Managers.sol";
import {AParameterStorage} from "../storages/Storages.sol";
import "../Platform.sol";

contract ParameterManager is AParameterManager, Named("parameter-manager"), Mortal, Component {

    string constant STORAGE = "parameter-manager";

    constructor(address registryAddress) Component(registryAddress) public {}

    function setVLFee(uint256 percent) public {
        require(percent >= 0, "The fee percent for vault logic cannot be less then zero");
        AParameterStorage(context.get(STORAGE)).setVLFee(percent);
    }

    function getVLFee() public view returns (uint256) {
        return AParameterStorage(context.get(STORAGE)).getVLFee();
    }

}