pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../repositories/application/IApplicationStorage.sol";
import "../oracles/IApplicationOracle.sol";

contract ApplicationManager is RegistryComponent {

    enum ApplicationStatus { PENDING, ENABLED, DISABLED }

    string constant COMPONENT_NAME = "application-manager";

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string name) {
        return COMPONENT_NAME;
    }

    function registerApplication(uint256 appId, string name, address owner, string url, address appAddr) external {
        _applicationStorage().save(appId, name, owner, url, appAddr, uint256(ApplicationStatus.PENDING));
    }

    function enableApplication(uint256 applicationId) external {
        _applicationStorage().setApplicationStatus(applicationId, uint256(ApplicationStatus.ENABLED));
    }

}