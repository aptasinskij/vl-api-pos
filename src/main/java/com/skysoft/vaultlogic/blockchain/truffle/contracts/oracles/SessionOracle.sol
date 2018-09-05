pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";

contract SessionOracle is RegistryComponent {

    string constant COMPONENT_NAME = "session-oracle";

    event CloseSession(string xToken);

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function closeSession(string xToken) external {
        emit CloseSession(xToken);
    }

}