pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";

contract ApplicationOracle is RegistryComponent {

    event ApplicationRegistered(uint256 index, string name, address owner, string url, address appAddr, uint256 status);

    string constant COMPONENT_NAME = "application-oracle";

    constructor(address registryAddr) RegistryComponent(registryAddr) public {}

    function getName() internal pure returns(string name) {
        return COMPONENT_NAME;
    }

    function register(uint256 index, string name, address owner, string url, address appAddr, uint256 status) external {
        emit ApplicationRegistered(index, name, owner, url, appAddr, status);
    }

}