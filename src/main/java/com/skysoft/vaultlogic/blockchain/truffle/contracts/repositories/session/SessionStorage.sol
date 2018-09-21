pragma solidity 0.4.24;

import "./SessionLib.sol";
import "../../registry/RegistryComponent.sol";
import "./ISessionStorage.sol";

contract SessionStorage is RegistryComponent, ISessionStorage {
    
    string constant COMPONENT_NAME = "session-storage";
    string constant DATABASE = "database";

    event Saved(uint256 sessionId, uint256 appId, string xToken, uint256 status);
    event StatusUpdated(uint256 index, uint256 status);
    event ActiveCashIn(uint256 _sessionId, bool _flag);

    using SessionLib for address;
    
    constructor(address registryAddress) RegistryComponent(registryAddress) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }
    
    function save(uint256 sessionId, uint256 appId, string xToken, uint256 status) external {
        lookup(DATABASE).save(sessionId, appId, xToken, status);
        emit Saved(sessionId, appId, xToken, status);
    }
    
    function getSession(uint256 index) public view returns(uint256 appId, string xToken, uint256 status) {
        return lookup(DATABASE).get(index);
    }

    function setHasActiveCashIn(uint256 _sessionId, bool _flag) external {
        lookup(DATABASE).setHasActiveCashIn(_sessionId, _flag);
    }

    function isHasActiveCashIn(uint256 _sessionId) external view returns(bool) {
        return lookup(DATABASE).getIsHasActiveCashIn(_sessionId);
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