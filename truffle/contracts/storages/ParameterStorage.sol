pragma solidity 0.4.24;

import {ParameterLib} from "../libs/Libraries.sol";
import "../platform/Named.sol";
import "../platform/Component.sol";
import "../platform/Mortal.sol";
import "./api/AParameterStorage.sol";

contract ParameterStorage is AParameterStorage, Named("parameter-storage"), Mortal, Component {

    using ParameterLib for address;

    constructor(address _config) Component(_config) public {}

    function setVLFee(uint256 percent) public {
        database.setVLFee(percent);
    }

    function getVLFee() public view returns (uint256) {
        return database.getVLFee();
    }

}
