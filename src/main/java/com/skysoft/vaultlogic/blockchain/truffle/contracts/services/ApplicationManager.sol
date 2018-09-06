pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../repositories/application/IApplicationStorage.sol";
import "../oracles/ApplicationOracleApi.sol";

contract ApplicationManager is RegistryComponent {

    enum ApplicationStatus { PENDING, ENABLED, DISABLED }

    string constant COMPONENT_NAME = "application-manager";

    string constant APPLICATION_STORAGE = "application-storage";
    string constant APPLICATION_ORACLE = "application-oracle";

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string name) {
        return COMPONENT_NAME;
    }

    function registerApplication(uint256 appId, string name, address owner, string url, address appAddr) external {
        IApplicationStorage(lookup(APPLICATION_STORAGE)).save(appId, name, owner, url, appAddr, uint256(ApplicationStatus.PENDING));
        ApplicationOracleApi(lookup(APPLICATION_ORACLE)).register(appId, name, owner, url, appAddr, uint256(ApplicationStatus.PENDING));
    }

    function enableApplication(uint256 applicationId) external {
        IApplicationStorage(lookup(APPLICATION_STORAGE)).setApplicationStatus(applicationId, uint256(ApplicationStatus.ENABLED));
    }

}