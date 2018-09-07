pragma solidity 0.4.24;

import "./SessionLib.sol";
import "../../registry/RegistryComponent.sol";

contract SessionStorage is RegistryComponent {
    
    string constant COMPONENT_NAME = "session-storage";
    string constant DATABASE = "database";

    event Saved(uint256 sessionId, uint256 appId, string xToken, uint256 status);
    event StatusUpdated(uint256 index, uint256 status);

    using SessionLib for address;
    
    constructor(address registryAddress) RegistryComponent(registryAddress) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }
    
    function save(uint256 sessionId, uint256 appId, string xToken, uint256 status) public {
        lookup(DATABASE).save(sessionId, appId, xToken, status);
        emit Saved(sessionId, appId, xToken, status);
    }
    
    function getSession(uint256 index) public view returns(uint256 appId, string xToken, uint256 status) {
        return lookup(DATABASE).get(index);
    }
    
    function getStatus(uint256 index) public view returns (uint256) {
        return lookup(DATABASE).getStatus(index);
    }

    function getStatusAndXToken(uint256 index) public view returns (uint256 status, string xToken) {
        status = getStatus(index);
        xToken = getXToken(index);
    }
    
    function getAppId(uint256 index) public view returns (uint256) {
        return lookup(DATABASE).getAppId(index);
    }
    
    function getXToken(uint256 index) public view returns (string) {
        return lookup(DATABASE).getXToken(index);
    }
    
    function getAppIdAndXToken(uint256 index) public view returns (uint256 appId, string xToken) {
        appId = getAppId(index);
        xToken = getXToken(index);
    }
    
    function setStatus(uint256 index, uint256 status) public {
        lookup(DATABASE).setStatus(index, status);
        emit StatusUpdated(index, status);
    }
 
}