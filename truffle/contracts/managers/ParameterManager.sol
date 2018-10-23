pragma solidity 0.4.24;

import "../Platform.sol";
import {AParameterManager} from "./Managers.sol";
import {ParameterLib} from "../libs/Libraries.sol";

contract ParameterManager is AParameterManager, Named("parameter-manager"), Mortal, Component {

    using ParameterLib for address;

    constructor(address _config) Component(_config) public {}

    function setVLFee(uint256 _vlFee) public {
        require(_vlFee > 0, "The fee percent for vault logic cannot be zero");
        database.setVLFee(_vlFee);
    }

    function getVLFee() public view returns (uint256 _vlFee) {
        _vlFee = database.getVLFee();
    }

}