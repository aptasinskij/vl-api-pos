pragma solidity 0.4.24;

import "../registry/Component.sol";
import {AnApplicationOracle} from "./Oracles.sol";

contract ApplicationOracle is AnApplicationOracle {

    event ApplicationRegistered(uint256 index, string name, address owner, string url, address appAddr, uint256 status);

    string constant COMPONENT_NAME = "application-oracle";

    constructor(address registryAddr) AnApplicationOracle(registryAddr) public {}

    function getName() internal pure returns(string name) {
        return COMPONENT_NAME;
    }

    function register(uint256 index, string name, address owner, string url, address appAddr, uint256 status) public {
        emit ApplicationRegistered(index, name, owner, url, appAddr, status);
    }

}