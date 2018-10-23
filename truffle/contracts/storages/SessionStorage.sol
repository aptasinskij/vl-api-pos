pragma solidity 0.4.24;

import {SessionLib} from "../libs/Libraries.sol";
import {ASessionStorage} from "./Storages.sol";
import "../Platform.sol";

contract SessionStorage is ASessionStorage, Named("session-storage"), Mortal, Component {
    
    using SessionLib for address;
    
    constructor(address _config) Component(_config) public {}
    
    function save(uint256 sessionId, uint256 appId, string xToken, uint256 status, string kioskId) public {
        database.save(sessionId, appId, xToken, status, kioskId);
        emit Saved(sessionId, appId, xToken, status, kioskId);
    }
    
    function getSession(uint256 index) public view returns(uint256 appId, string xToken, uint256 status) {
        return database.get(index);
    }

    function setHasActiveCashIn(uint256 _sessionId, bool _flag) public {
        database.setSessionHasActiveCashIn(_sessionId, _flag);
    }

    function isHasActiveCashIn(uint256 _sessionId) public view returns(bool) {
        return database.getIsHasActiveCashIn(_sessionId);
    }
    
    function getStatus(uint256 index) public view returns (uint256) {
        return uint256(database.getStatus(index));
    }

    function getStatusAndXToken(uint256 index) public view returns (uint256 status, string xToken) {
        status = uint256(getStatus(index));
        xToken = getXToken(index);
    }
    
    function getAppId(uint256 index) public view returns (uint256) {
        return database.getAppId(index);
    }
    
    function getXToken(uint256 index) public view returns (string) {
        return database.getXToken(index);
    }
    
    function getAppIdAndXToken(uint256 index) public view returns (uint256 appId, string xToken) {
        appId = getAppId(index);
        xToken = getXToken(index);
    }
    
    function setStatus(uint256 index, uint256 status) public {
        database.setStatus(index, SessionLib.Status(status));
        emit StatusUpdated(index, status);
    }
 
}