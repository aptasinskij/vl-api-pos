pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../repositories/application/IApplicationStorage.sol";

contract AbstractController is RegistryComponent {

    modifier onlyRegisteredApp {
        require(_applicationStorage().isRegistered(msg.sender), "Illegal access");
        _;
    }

    constructor(address registry) RegistryComponent(registry) public {}

}
