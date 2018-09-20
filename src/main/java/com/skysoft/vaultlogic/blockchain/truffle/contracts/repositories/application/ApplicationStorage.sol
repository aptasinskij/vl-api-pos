pragma solidity 0.4.24;

import "./ApplicationLib.sol";
import "../../registry/RegistryComponent.sol";
import "./IApplicationStorage.sol";

contract ApplicationStorage is RegistryComponent, IApplicationStorage {

    string constant COMPONENT_NAME = "application-storage";
    string constant DATABASE = "database";

    event ApplicationSaved(uint256 appId, string name, address owner, string url, address appAddr, uint256 status);
    event ApplicationUrlUpdated(uint256 appId, string url);
    event ApplicationAddressUpdated(uint256 appId, address appAddr);
    event ApplicationStatusUpdated(uint256 appId, uint256 status);

    using ApplicationLib for address;

    constructor(address registryAddr) RegistryComponent(registryAddr) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function save(uint256 appId, string name, address owner, string url, address appAddr, uint256 status) external {
        lookup(DATABASE).save(appId, name, owner, url, appAddr, status);
        emit ApplicationSaved(appId, name, owner, url, appAddr, status);
    }

    function get(uint256 appId) external view returns (string, address, string, address, uint256) {
        return lookup(DATABASE).get(appId);
    }

    function isRegistered(address _applicationAddress) external view returns(bool) {
        return lookup(DATABASE).isRegistered(_applicationAddress);
    }

    function getApplicationName(uint256 appId) external view returns(string) {
        return lookup(DATABASE).getName(appId);
    }

    function getApplicationOwner(uint256 appId) external view returns(address) {
        return lookup(DATABASE).getOwner(appId);
    }

    function getApplicationUrl(uint256 appId) external view returns(string) {
        return lookup(DATABASE).getUrl(appId);
    }

    function setApplicationUrl(uint256 appId, string url) external {
        lookup(DATABASE).setUrl(appId, url);
        emit ApplicationUrlUpdated(appId, url);
    }

    function getApplicationAddress(uint256 appId) external view returns(address) {
        return lookup(DATABASE).getAddress(appId);
    }

    function setApplicationAddress(uint256 appId, address appAddr) external {
        lookup(DATABASE).setAddress(appId, appAddr);
        emit ApplicationAddressUpdated(appId, appAddr);
    }

    function getApplicationStatus(uint256 appId) external view returns(uint256) {
        return lookup(DATABASE).getStatus(appId);
    }

    function setApplicationStatus(uint256 appId, uint256 status) external {
        lookup(DATABASE).setStatus(appId, status);
        emit ApplicationStatusUpdated(appId, status);
    }

}