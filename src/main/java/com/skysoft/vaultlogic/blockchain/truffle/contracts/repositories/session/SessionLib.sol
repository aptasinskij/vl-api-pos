pragma solidity 0.4.24;

import "../../storage/Database.sol";

library SessionLib {
    
    string constant APP_ID = "session_application_id";
    string constant X_TOKEN = "session_x_token";
    string constant STATUS = "session_status";
        
    function save(address self, uint256 sessionId, uint256 appId, string xToken, uint256 status) internal {
        Database(self).setUintValue(encode(APP_ID, sessionId), appId);
        Database(self).setStringValue(encode(X_TOKEN, sessionId), xToken);
        Database(self).setUintValue(encode(STATUS, sessionId), status);
    }
    
    function get(address self, uint256 index) internal view returns (uint256 appId, string xToken, uint256 status) {
        appId = Database(self).getUintValue(encode(APP_ID, index));
        xToken = Database(self).getStringValue(encode(X_TOKEN, index));
        status = Database(self).getUintValue(encode(STATUS, index));
    }
    
    function getAppId(address self, uint256 index) internal view returns (uint256) {
        Database(self).getUintValue(encode(APP_ID, index));
    }
    
    function getXToken(address self, uint256 index) internal view returns (string) {
        Database(self).getStringValue(encode(X_TOKEN, index));
    }
    
    function getStatus(address self, uint256 index) internal view returns (uint256) {
        Database(self).getUintValue(encode(STATUS, index));
    }
        
    function setStatus(address self, uint256 index, uint256 status) public {
        Database(self).setUintValue(encode(STATUS, index), status);
    }
    
    function encode(string row, uint256 counter) private pure returns(bytes32) {
        return keccak256(abi.encodePacked(row, counter));
    }
    
}