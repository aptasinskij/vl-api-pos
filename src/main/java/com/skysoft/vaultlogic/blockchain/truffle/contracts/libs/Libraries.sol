pragma solidity 0.4.24;

import "../Database.sol";
import "./Libraries.sol";

library SafeMath {

    function mul(uint256 a, uint256 b) internal pure returns (uint256) {
        if (a == 0) {
            return 0;
        }

        uint256 c = a * b;
        require(c / a == b);

        return c;
    }

    function div(uint256 a, uint256 b) internal pure returns (uint256) {
        require(b > 0);
        uint256 c = a / b;

        return c;
    }

    function sub(uint256 a, uint256 b) internal pure returns (uint256) {
        require(b <= a);
        uint256 c = a - b;

        return c;
    }

    function add(uint256 a, uint256 b) internal pure returns (uint256) {
        uint256 c = a + b;
        require(c >= a);

        return c;
    }

    function mod(uint256 a, uint256 b) internal pure returns (uint256) {
        require(b != 0);
        return a % b;
    }

}

library ApplicationLib {

    string constant ID = "application_id";
    string constant NAME = "application_name";
    string constant OWNER = "application_owner";
    string constant URL = "application_url";
    string constant ADDRESS = "application_address";
    string constant STATUS = "application_status";
    string constant REGISTERED = "application_registered";

    function save(address self, uint256 appId, string name, address owner, string url, address appAddr, uint256 status) internal {
        Database(self).setUintValue(string256(ID, appId), appId);
        Database(self).setStringValue(string256(NAME, appId), name);
        Database(self).setAddressValue(string256(OWNER, appId), owner);
        Database(self).setStringValue(string256(URL, appId), url);
        Database(self).setAddressValue(string256(ADDRESS, appId), appAddr);
        Database(self).setUintValue(string256(STATUS, appId), status);
        Database(self).setBooleanValue(keccak256(abi.encodePacked(REGISTERED, appAddr)), true);
    }

    function isRegistered(address self, address _applicationAddress) internal view returns (bool) {
        return Database(self).getBooleanValue(keccak256(abi.encodePacked(REGISTERED, _applicationAddress)));
    }

    function get(address self, uint256 appId) internal view returns (string name, address owner, string url, address appAddr, uint256 status) {
        name = Database(self).getStringValue(string256(NAME, appId));
        owner = Database(self).getAddressValue(string256(OWNER, appId));
        url = Database(self).getStringValue(string256(URL, appId));
        appAddr = Database(self).getAddressValue(string256(ADDRESS, appId));
        status = Database(self).getUintValue(string256(STATUS, appId));
    }

    function getName(address self, uint256 appId) internal view returns (string) {
        return Database(self).getStringValue(string256(NAME, appId));
    }

    function getOwner(address self, uint256 appId) internal view returns (address) {
        return Database(self).getAddressValue(string256(OWNER, appId));
    }

    function getUrl(address self, uint256 appId) internal view returns (string) {
        return Database(self).getStringValue(string256(URL, appId));
    }

    function setUrl(address self, uint256 appId, string url) internal {
        Database(self).setStringValue(string256(URL, appId), url);
    }

    function getAddress(address self, uint256 appId) internal view returns (address) {
        return Database(self).getAddressValue(string256(ADDRESS, appId));
    }

    function setAddress(address self, uint256 appId, address appAddr) internal {
        Database(self).setAddressValue(string256(ADDRESS, appId), appAddr);
    }

    function getStatus(address self, uint256 appId) internal view returns (uint256) {
        return Database(self).getUintValue(string256(STATUS, appId));
    }

    function setStatus(address self, uint256 appId, uint256 status) internal {
        Database(self).setUintValue(string256(STATUS, appId), status);
    }

    function string256(string field, uint256 appId) private pure returns (bytes32) {
        return keccak256(abi.encodePacked(field, appId));
    }

}

library CashInLib {

    bytes32 constant CASH_IN_INDEX = keccak256(abi.encode("CashInIndex"));

    string constant SESSION_ID = "cash_in_session_id";
    string constant APPLICATION = "cash_in_application";
    string constant STATUS = "cash_in_status";
    string constant BALANCE = "cash_in_balance";
    string constant VL_FEE = "cash_in_vault_logic_fee";
    string constant APP_BALANCE = "cash_in_application_balance";

    ///@dev set of constants to store and retrieve dynamic sized arrays of splits
    string constant SPLIT_SIZE = "cash_in_split_size";
    string constant SPLIT_PARTIES = "cash_in_split_parties";
    string constant SPLIT_FEES = "cash_in_split_fees";

    function getCounter(address self) internal view returns(uint256) {
        Database(self).getUintValue(CASH_IN_INDEX);
    }

    function save(address self, uint256 sessionId, address application, uint256 status) internal returns(uint256 index) {
        index = Database(self).getUintValue(CASH_IN_INDEX);
        Database(self).setUintValue(string256(SESSION_ID, index), sessionId);
        Database(self).setAddressValue(string256(APPLICATION, index), application);
        Database(self).setUintValue(string256(STATUS, index), status);
        Database(self).setUintValue(CASH_IN_INDEX, index + 1);
    }

    /// @dev size of both arrays (e.g. parties and fees) stored in separate field 'splitSize'
    function get(address self, uint256 index) internal view returns(
        uint256 sessionId,
        address application,
        uint256 balance,
        uint256 status,
        uint256 splitSize
    ) {
        sessionId = Database(self).getUintValue(string256(SESSION_ID, index));
        application = Database(self).getAddressValue(string256(APPLICATION, index));
        balance = Database(self).getUintValue(string256(BALANCE, index));
        status = Database(self).getUintValue(string256(STATUS, index));
        splitSize = Database(self).getUintValue(string256(SPLIT_SIZE, index));
    }

    function getSessionId(address self, uint256 index) internal view returns(uint256) {
        return Database(self).getUintValue(string256(SESSION_ID, index));
    }

    function getApplication(address self, uint256 index) internal view returns(address) {
        return Database(self).getAddressValue(string256(APPLICATION, index));
    }

    function setBalance(address self, uint256 index, uint256 amount) internal {
        Database(self).setUintValue(string256(BALANCE, index), amount);
    }

    function getBalance(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(string256(BALANCE, index));
    }

    function setVLFee(address self, uint256 index, uint256 fee) internal {
        Database(self).setUintValue(keccak256(abi.encodePacked(VL_FEE, index)), fee);
    }

    function getVLFee(address self, uint256 index) internal view returns(uint256) {
        return Database(self).getUintValue(keccak256(abi.encodePacked(VL_FEE, index)));
    }

    function setApplicationBalance(address self, uint256 index, uint256 balance) internal {
        Database(self).setUintValue(string256(APP_BALANCE, index), balance);
    }

    function getApplicationBalance(address self, uint256 index) internal view returns(uint256) {
        return Database(self).getUintValue(string256(APP_BALANCE, index));
    }

    function setStatus(address self, uint256 index, uint256 status) internal {
        Database(self).setUintValue(string256(STATUS, index), uint256(status));
    }

    function getStatus(address self, uint256 index) internal view returns(uint256) {
        return Database(self).getUintValue(string256(STATUS, index));
    }

    function addSplit(address self, uint256 index, address receiver, uint256 amount) internal {
        uint256 splitSize = Database(self).getUintValue(string256(SPLIT_SIZE, index));
        Database(self).setAddressValue(string256x2(SPLIT_PARTIES, index, splitSize), receiver);
        Database(self).setUintValue(string256x2(SPLIT_FEES, index, splitSize), amount);
        Database(self).setUintValue(string256(SPLIT_SIZE, index), splitSize + 1);
    }

    function addSplits(address self, uint256 index, address[] receivers, uint256[] amounts) internal {
        for (uint256 i = 0; i < receivers.length; i++) {
            addSplit(self, index, receivers[i], amounts[i]);
        }
    }

    function getSplitSize(address self, uint256 index) internal view returns(uint256) {
        return Database(self).getUintValue(string256(SPLIT_SIZE, index));
    }

    function getSplit(address self, uint256 index, uint256 subIndex) internal view returns(address party, uint256 fee) {
        party = Database(self).getAddressValue(string256x2(SPLIT_PARTIES, index, subIndex));
        fee = Database(self).getUintValue(string256x2(SPLIT_FEES, index, subIndex));
    }

    function string256(string field, uint256 index) private pure returns(bytes32) {
        return keccak256(abi.encodePacked(field, index));
    }

    function string256x2(string field, uint256 index, uint256 counter) private pure returns(bytes32) {
        return keccak256(abi.encodePacked(field, index, counter));
    }

}

library ParameterLib {

    string constant VAULT_LOGIC_FEE_PERCENT = "vault_logic_fee_percent";

    function setVLFee(address self, uint256 percent) internal {
        Database(self).setUintValue(keccak256(abi.encode(VAULT_LOGIC_FEE_PERCENT)), percent);
    }

    function getVLFee(address self) internal view returns (uint256) {
        return Database(self).getUintValue(keccak256(abi.encode(VAULT_LOGIC_FEE_PERCENT)));
    }

}

library KioskLib {

    string constant SHORT_ID = "kiosk_short_id";
    string constant LOCATION = "kiosk_location_address";
    string constant NAME = "kiosk_name";
    string constant TIME_ZONE = "kiosk_time_zone";

    function saveKiosk(address self, string shortId, string locationAddress, string name, string timeZone) internal {
        Database(self).setStringValue(keccak256(abi.encode(SHORT_ID, shortId)), shortId);
        Database(self).setStringValue(keccak256(abi.encode(LOCATION, shortId)), locationAddress);
        Database(self).setStringValue(keccak256(abi.encode(NAME, shortId)), name);
        Database(self).setStringValue(keccak256(abi.encode(TIME_ZONE, shortId)), timeZone);
    }

    function retrieveKiosk(address self, string shortId) internal view returns(string locationAddress, string name, string timeZone) {
        locationAddress = Database(self).getStringValue(keccak256(abi.encode(LOCATION, shortId)));
        name = Database(self).getStringValue(keccak256(abi.encode(NAME, shortId)));
        timeZone = Database(self).getStringValue(keccak256(abi.encode(TIME_ZONE, shortId)));
    }

}

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

    function get(address self, uint256 index) internal view returns (uint256 appId, string xToken, uint256 status) {
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

    function getXToken(address self, uint256 index) internal view returns (string) {
        return Database(self).getStringValue(keccak256(abi.encodePacked(X_TOKEN, index)));
    }

    function getStatus(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(keccak256(abi.encodePacked(STATUS, index)));
    }

    function setStatus(address self, uint256 index, uint256 status) internal {
        Database(self).setUintValue(keccak256(abi.encodePacked(STATUS, index)), status);
    }

}

library TokenLib {

    string constant TOKEN_AMOUNT = "token_amount";

    function set(address self, address customer, uint256 amount) internal {
        Database(self).setUintValue(keccak256(abi.encodePacked(TOKEN_AMOUNT, customer)), amount);
    }

    function get(address self, address customer) internal view returns (uint256) {
        return Database(self).getUintValue(keccak256(abi.encodePacked(TOKEN_AMOUNT, customer)));
    }

}