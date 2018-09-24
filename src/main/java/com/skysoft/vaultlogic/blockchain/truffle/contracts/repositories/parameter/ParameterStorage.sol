pragma solidity 0.4.24;

import "../../registry/Component.sol";
import "./ParameterLib.sol";

contract ParameterStorage is Component {

    string constant COMPONENT_NAME = "parameter-storage";
    string constant DATABASE = "database";

    using ParameterLib for address;

    constructor(address registryAddress) Component(registryAddress) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function setVLFee(uint256 percent) public {
        lookup(DATABASE).setVLFee(percent);
    }

    function getVLFee() public view returns (uint256) {
        return lookup(DATABASE).getVLFee();
    }

}
