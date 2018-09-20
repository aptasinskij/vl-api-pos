pragma solidity 0.4.24;

import "../../storage/Database.sol";

library SessionLib {

    string constant APP_ID = "session_application_id";
    string constant X_TOKEN = "session_x_token";
    string constant STATUS = "session_status";
    string constant HAS_ACTIVE_CASH_IN = "session_has_active_cash_in";

    function save(address self, uint256 sessionId, uint256 appId, string xToken, uint256 status) internal {
        Database(self).setUintValue(keccak256(abi.encodePacked(APP_ID, sessionId)), appId);
        Database(self).setStringValue(keccak256(abi.encodePacked(X_TOKEN, sessionId)), xToken);
        Database(self).setUintValue(keccak256(abi.encodePacked(STATUS, sessionId)), status);
    }

    function get(address self, uint256 index) public view returns (uint256 appId, string xToken, uint256 status) {
        appId = Database(self).getUintValue(keccak256(abi.encodePacked(APP_ID, index)));
        xToken = Database(self).getStringValue(keccak256(abi.encodePacked(X_TOKEN, index)));
        status = Database(self).getUintValue(keccak256(abi.encodePacked(STATUS, index)));
    }

    function setHasActiveCashIn(address _self, uint256 _sessionId, bool _flag) internal {
        Database(_self).setBooleanValue(keccak256(abi.encodePacked(HAS_ACTIVE_CASH_IN, _sessionId)), _flag);
    }

    function getIsHasActiveCashIn(address _self, uint256 _sessionId) internal view returns(bool) {
        return Database(_self).getBooleanValue(keccak256(abi.encodePacked(HAS_ACTIVE_CASH_IN, _sessionId)));
    }

    function getAppId(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(keccak256(abi.encodePacked(APP_ID, index)));
    }

    function getXToken(address self, uint256 index) public view returns (string) {
        return Database(self).getStringValue(keccak256(abi.encodePacked(X_TOKEN, index)));
    }

    function getStatus(address self, uint256 index) public view returns (uint256) {
        return Database(self).getUintValue(keccak256(abi.encodePacked(STATUS, index)));
    }

    function setStatus(address self, uint256 index, uint256 status) internal {
        Database(self).setUintValue(keccak256(abi.encodePacked(STATUS, index)), status);
    }
    
}