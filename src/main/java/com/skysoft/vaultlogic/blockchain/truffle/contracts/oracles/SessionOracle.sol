pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";

contract SessionOracle is RegistryComponent {

    string constant COMPONENT_NAME = "session-oracle";

    event CloseSession(uint256 sessionId);

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function closeSession(uint256 sessionId) external {
        emit CloseSession(sessionId);
    }

}