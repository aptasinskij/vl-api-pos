pragma solidity 0.4.24;

import "../registry/Component.sol";

contract AbstractController is Component {

    modifier onlyRegisteredApp {
        require(_applicationStorage().isRegistered(msg.sender), "Illegal access");
        _;
    }

    constructor(address registry) Component(registry) public {}

}
