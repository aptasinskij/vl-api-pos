pragma solidity 0.4.24;

import "../registry/Component.sol";
import "./AnApplicationManager.sol";

contract ApplicationManager is Component, AnApplicationManager {

    string constant COMPONENT_NAME = "application-manager";

    constructor(address regAddr) Component(regAddr) public {}

    function getName() internal pure returns(string name) {
        return COMPONENT_NAME;
    }

    function registerApplication(uint256 appId, string name, address owner, string url, address appAddr) public {
        _applicationStorage().save(appId, name, owner, url, appAddr, uint256(ApplicationStatus.PENDING));
    }

    function enableApplication(uint256 applicationId) public {
        _applicationStorage().setApplicationStatus(applicationId, uint256(ApplicationStatus.ENABLED));
    }

}