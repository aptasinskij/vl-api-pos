pragma solidity 0.4.24;

import "../../storage/Database.sol";

library ApplicationLib {
    
    string constant ID = "application_id";
    string constant NAME = "application_name";
    string constant OWNER = "application_owner";
    string constant URL = "application_url";
    string constant ADDRESS = "application_address";
    string constant STATUS = "application_status";
    string constant REGISTERED = "application_registered";

    function save(address self, uint256 appId, string name, address owner, string url, address appAddr, uint256 status) public {
        Database(self).setUintValue(string256(ID, appId), appId);
        Database(self).setStringValue(string256(NAME, appId), name);
        Database(self).setAddressValue(string256(OWNER, appId), owner);
        Database(self).setStringValue(string256(URL, appId), url);
        Database(self).setAddressValue(string256(ADDRESS, appId), appAddr);
        Database(self).setUintValue(string256(STATUS, appId), status);
        Database(self).setBooleanValue(keccak256(abi.encodePacked(REGISTERED, appAddr)), true);
    }

    function isRegistered(address self, address _applicationAddress) internal view returns(bool _registered) {
        _registered = Database(self).getBooleanValue(keccak256(abi.encode(REGISTERED, _applicationAddress)));
    }

    function get(address self, uint256 appId) public view returns(string name, address owner, string url, address appAddr, uint256 status) {
        name = Database(self).getStringValue(string256(NAME, appId));
        owner = Database(self).getAddressValue(string256(OWNER, appId));
        url = Database(self).getStringValue(string256(URL, appId));
        appAddr = Database(self).getAddressValue(string256(ADDRESS, appId));
        status = Database(self).getUintValue(string256(STATUS, appId));
    }

    function getName(address self, uint256 appId) public view returns(string) {
        return Database(self).getStringValue(string256(NAME, appId));
    }

    function getOwner(address self, uint256 appId) public view returns(address) {
        return Database(self).getAddressValue(string256(OWNER, appId));
    }

    function getUrl(address self, uint256 appId) public view returns(string) {
        return Database(self).getStringValue(string256(URL, appId));
    }

    function setUrl(address self, uint256 appId, string url) public {
        Database(self).setStringValue(string256(URL, appId), url);
    }

    function getAddress(address self, uint256 appId) public view returns(address) {
        return Database(self).getAddressValue(string256(ADDRESS, appId));
    }

    function setAddress(address self, uint256 appId, address appAddr) public {
        Database(self).setAddressValue(string256(ADDRESS, appId), appAddr);
    }

    function getStatus(address self, uint256 appId) public view returns(uint256) {
        return Database(self).getUintValue(string256(STATUS, appId));
    }

    function setStatus(address self, uint256 appId, uint256 status) public {
        Database(self).setUintValue(string256(STATUS, appId), status);
    }

    function string256(string field, uint256 appId) private pure returns(bytes32) {
        return keccak256(abi.encodePacked(field, appId));
    }

} 