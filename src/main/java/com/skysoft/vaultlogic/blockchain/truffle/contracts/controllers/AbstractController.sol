pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../repositories/application/IApplicationStorage.sol";

contract AbstractController is RegistryComponent {

    string constant APPLICATION_STORAGE = "application-storage";

    modifier onlyRegisteredApp {
        require(IApplicationStorage(lookup(APPLICATION_STORAGE)).isRegistered(msg.sender), "Illegal access");
        _;
    }

    constructor(address registry) RegistryComponent(registry) public {}

}
