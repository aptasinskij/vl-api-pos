pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/AnApplicationManager.sol";
import "../storages/api/AnApplicationStorage.sol";

contract ApplicationManager is AnApplicationManager, Named("application-manager"), Mortal, Component {

    string constant STORAGE = "application-storage";

    constructor(address _config) Component(_config) public {}

    function registerApplication(uint256 appId, string name, address owner, string url, address appAddr) public {
        AnApplicationStorage(context.get(STORAGE)).save(appId, name, owner, url, appAddr, uint256(ApplicationStatus.PENDING));
    }

    function enableApplication(uint256 applicationId) public {
        AnApplicationStorage(context.get(STORAGE)).setApplicationStatus(applicationId, uint256(ApplicationStatus.ENABLED));
    }

}