pragma solidity 0.4.24;

import "../Database.sol";

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

    string constant EXISTS = "application.exists";
    string constant ID = "application_id";
    string constant NAME = "application_name";
    string constant OWNER = "application_owner";
    string constant URL = "application_url";
    string constant ADDRESS = "application_address";
    string constant STATUS = "application_status";
    string constant REGISTERED = "application_registered";

    enum Status {PENDING, ENABLED, DISABLED}

    struct Application {
        uint256 id;
        string name;
        address owner;
        string url;
        address deployedAddress;
        Status status;
    }

    function applicationExists(address self, uint256 applicationId) internal view returns (bool) {
        return Database(self).getBooleanValue(keccak256(abi.encodePacked(EXISTS, applicationId)));
    }

    function createApplication(address self, Application memory application) internal returns (bool) {
        require(!applicationExists(self, application.id), "Application already exists");
        Database(self).setUintValue(keccak256(abi.encodePacked(ID, application.id)), application.id);
        Database(self).setStringValue(keccak256(abi.encodePacked(NAME, application.id)), application.name);
        Database(self).setAddressValue(keccak256(abi.encodePacked(OWNER, application.id)), application.owner);
        Database(self).setStringValue(keccak256(abi.encodePacked(URL, application.id)), application.url);
        Database(self).setAddressValue(keccak256(abi.encodePacked(ADDRESS, application.id)), application.deployedAddress);
        Database(self).setUintValue(keccak256(abi.encodePacked(STATUS, application.id)), uint256(application.status));
        Database(self).setBooleanValue(keccak256(abi.encodePacked(REGISTERED, application.deployedAddress)), true);
        Database(self).setBooleanValue(keccak256(abi.encodePacked(EXISTS, application.id)), true);
        return true;
    }

    function retrieveApplication(address self, uint256 applicationId) internal view returns (Application memory) {
        require(applicationExists(self, applicationId), "Application is not exists");
        return Application({
            id : applicationId,
            name : Database(self).getStringValue(keccak256(abi.encodePacked(NAME, applicationId))),
            owner : Database(self).getAddressValue(keccak256(abi.encodePacked(OWNER, applicationId))),
            url : Database(self).getStringValue(keccak256(abi.encodePacked(URL, applicationId))),
            deployedAddress : Database(self).getAddressValue(keccak256(abi.encodePacked(ADDRESS, applicationId))),
            status : Status(Database(self).getUintValue(keccak256(abi.encodePacked(ID, applicationId))))
            });
    }

    function save(address self, uint256 appId, string name, address owner, string url, address appAddr, uint256 status) internal {
        require(!applicationExists(self, appId), "Application already exists");
        Database(self).setUintValue(string256(ID, appId), appId);
        Database(self).setStringValue(string256(NAME, appId), name);
        Database(self).setAddressValue(string256(OWNER, appId), owner);
        Database(self).setStringValue(string256(URL, appId), url);
        Database(self).setAddressValue(string256(ADDRESS, appId), appAddr);
        Database(self).setUintValue(string256(STATUS, appId), status);
        Database(self).setBooleanValue(keccak256(abi.encodePacked(REGISTERED, appAddr)), true);
        Database(self).setBooleanValue(keccak256(abi.encodePacked(EXISTS, appId)), true);
    }

    function isRegistered(address self, address _applicationAddress) internal view returns (bool) {
        return Database(self).getBooleanValue(keccak256(abi.encodePacked(REGISTERED, _applicationAddress)));
    }

    function get(address self, uint256 appId) internal view returns (string name, address owner, string url, address appAddr, uint256 status) {
        require(applicationExists(self, appId), "Application is not exists");
        name = Database(self).getStringValue(string256(NAME, appId));
        owner = Database(self).getAddressValue(string256(OWNER, appId));
        url = Database(self).getStringValue(string256(URL, appId));
        appAddr = Database(self).getAddressValue(string256(ADDRESS, appId));
        status = Database(self).getUintValue(string256(STATUS, appId));
    }

    function getName(address self, uint256 appId) internal view returns (string) {
        require(applicationExists(self, appId), "Application is not exists");
        return Database(self).getStringValue(string256(NAME, appId));
    }

    function getOwner(address self, uint256 appId) internal view returns (address) {
        require(applicationExists(self, appId), "Application is not exists");
        return Database(self).getAddressValue(string256(OWNER, appId));
    }

    function getUrl(address self, uint256 appId) internal view returns (string) {
        require(applicationExists(self, appId), "Application is not exists");
        return Database(self).getStringValue(string256(URL, appId));
    }

    function setUrl(address self, uint256 appId, string url) internal {
        require(applicationExists(self, appId), "Application is not exists");
        Database(self).setStringValue(string256(URL, appId), url);
    }

    function getAddress(address self, uint256 appId) internal view returns (address) {
        require(applicationExists(self, appId), "Application is not exists");
        return Database(self).getAddressValue(string256(ADDRESS, appId));
    }

    function setAddress(address self, uint256 appId, address appAddr) internal {
        require(applicationExists(self, appId), "Application is not exists");
        Database(self).setAddressValue(string256(ADDRESS, appId), appAddr);
    }

    function getStatus(address self, uint256 appId) internal view returns (uint256) {
        require(applicationExists(self, appId), "Application is not exists");
        return Database(self).getUintValue(string256(STATUS, appId));
    }

    function setStatus(address self, uint256 appId, uint256 status) internal {
        require(applicationExists(self, appId), "Application is not exists");
        Database(self).setUintValue(string256(STATUS, appId), status);
    }

    function string256(string field, uint256 appId) private pure returns (bytes32) {
        return keccak256(abi.encodePacked(field, appId));
    }

}

/*library KioskLib {

    string constant EXISTS = "kiosk.exists";

    string constant ID = "kiosk_short_id";
    string constant LOCATION = "kiosk_location_address";
    string constant NAME = "kiosk_name";
    string constant TIME_ZONE = "kiosk_time_zone";

    struct Kiosk {
        string id;
        string location;
        string name;
        string timezone;
    }

    // check existence
    function kioskExists(address self, string memory kioskId) internal view returns (bool) {
        return Database(self).getBooleanValue(keccak256(abi.encodePacked(EXISTS, kioskId)));
    }

    // create
    function createKiosk(address self, Kiosk memory kiosk) internal returns (bool) {
        require(!kioskExists(self, kiosk.id), "Kiosk already exists");
        Database(self).setStringValue(keccak256(abi.encodePacked(ID, kiosk.id)), kiosk.id);
        Database(self).setStringValue(keccak256(abi.encodePacked(LOCATION, kiosk.id)), kiosk.location);
        Database(self).setStringValue(keccak256(abi.encodePacked(NAME, kiosk.id)), kiosk.name);
        Database(self).setStringValue(keccak256(abi.encodePacked(TIME_ZONE, kiosk.id)), kiosk.timezone);
        Database(self).setBooleanValue(keccak256(abi.encodePacked(EXISTS, kiosk.id)), true);
        return true;
    }

    function save(address self, string memory _kioskId, string memory _location, string memory _name, string memory _timezone) internal returns (bool) {
        require(!kioskExists(self, _kioskId), "Kiosk already exists");
        Database(self).setStringValue(keccak256(abi.encodePacked(ID, _kioskId)), _kioskId);
        Database(self).setStringValue(keccak256(abi.encodePacked(LOCATION, _kioskId)), _location);
        Database(self).setStringValue(keccak256(abi.encodePacked(NAME, _kioskId)), _name);
        Database(self).setStringValue(keccak256(abi.encodePacked(TIME_ZONE, _kioskId)), _timezone);
        Database(self).setBooleanValue(keccak256(abi.encodePacked(EXISTS, _kioskId)), true);
        return true;
    }

    // read
    function retrieveKiosk(address self, string memory _kioskId) internal view returns (Kiosk memory) {
        require(kioskExists(self, _kioskId), "Kiosk is not exists");
        // @formatter:off
        return Kiosk({
            id: _kioskId,
            location: Database(self).getStringValue(keccak256(abi.encodePacked(LOCATION, _kioskId))),
            name: Database(self).getStringValue(keccak256(abi.encodePacked(NAME, _kioskId))),
            timezone: Database(self).getStringValue(keccak256(abi.encodePacked(TIME_ZONE, _kioskId)))
        });
        // @formatter:on
    }

    function get(address self, string memory _kioskId) internal view returns (string memory _location, string memory _name, string memory _timezone) {
        require(kioskExists(self, _kioskId), "Kiosk is not exists");
        _location = Database(self).getStringValue(keccak256(abi.encodePacked(LOCATION, _kioskId)));
        _name = Database(self).getStringValue(keccak256(abi.encodePacked(NAME, _kioskId)));
        _timezone = Database(self).getStringValue(keccak256(abi.encodePacked(TIME_ZONE, _kioskId)));
    }

}*/

/*library CashOutLib {

    using ApplicationLib for address;
    *//*using KioskLib for address;*//*

    bytes32 constant CASH_OUT_INDEX = keccak256(abi.encode("CashOutIndex"));

    string constant EXISTS = "cash-out.exists";
    string constant KIOSK_ID = "cash-out.kiosk.id";
    string constant SESSION_ID = "cash-out.session.id";
    string constant APPLICATION = "cash-out.application.address";
    string constant STATUS = "cash-out.status";
    string constant VL_FEE = "cash-out.vault_logic-percent";
    string constant VL_AMOUNT = "cash-in.vault_logic_amount";
    string constant WITHDRAW_AMOUNT = "cash-out.withdraw_amount";
    string constant RESERVED_AMOUNT = "cash-out.reserved_amount";

    string constant SPLIT_SIZE = "cash-out.split_size";
    string constant SPLIT_PARTIES = "cash-out.split_parties";
    string constant SPLIT_FEES = "cash-out.split_fees";

    enum Status {ACTIVE, VALIDATING, NOT_ABLE_TO_CLOSE, ABLE_TO_CLOSE, CLOSE_REQUESTED, CLOSED, CANCELED}

    struct CashOut {
        uint256 id;
        string kioskId;
        uint256 sessionId;
        address application;
        Status status;
        uint256 vaultLogicPercent;
        uint256 vaultLogicAmount;
        uint256 withdrawAmount;
        uint256 reservedAmount;
        uint256 splitSize;
        address[] parties;
        uint256[] fees;
    }

    function getCounter(address self) internal view returns (uint256 _counter) {
        _counter = Database(self).getUintValue(CASH_OUT_INDEX);
    }

    function cashOutExists(address self, uint256 cashOutId) internal view returns (bool _exists) {
        _exists = Database(self).getBooleanValue(keccak256(abi.encodePacked(EXISTS, cashOutId)));
    }

    function createCashOut(address self, CashOut memory cashOut) internal returns (uint256 index) {
        index = Database(self).getUintValue(CASH_OUT_INDEX);
        Database(self).setStringValue(keccak256(abi.encodePacked(KIOSK_ID, index)), cashOut.kioskId);
        Database(self).setUintValue(keccak256(abi.encodePacked(SESSION_ID, index)), uint256(cashOut.sessionId));
        Database(self).setAddressValue(keccak256(abi.encodePacked(APPLICATION, index)), cashOut.application);
        Database(self).setUintValue(keccak256(abi.encodePacked(STATUS, index)), uint256(cashOut.status));
        Database(self).setUintValue(keccak256(abi.encodePacked(VL_FEE, index)), cashOut.vaultLogicPercent);
        Database(self).setUintValue(keccak256(abi.encodePacked(VL_AMOUNT, index)), cashOut.vaultLogicAmount);
        Database(self).setUintValue(keccak256(abi.encodePacked(WITHDRAW_AMOUNT, index)), cashOut.withdrawAmount);
        Database(self).setUintValue(keccak256(abi.encodePacked(RESERVED_AMOUNT, index)), cashOut.reservedAmount);
        addSplits(self, index, cashOut.parties, cashOut.fees);
        Database(self).setBooleanValue(keccak256(abi.encodePacked(EXISTS, index)), true);
        Database(self).setUintValue(CASH_OUT_INDEX, index + 1);
    }

    function addSplits(address self, uint256 index, address[] receivers, uint256[] amounts) internal {
        for (uint256 i = 0; i < receivers.length; i++) {
            addSplit(self, index, receivers[i], amounts[i]);
        }
    }

    function addSplit(address self, uint256 index, address receiver, uint256 amount) internal {
        uint256 splitSize = Database(self).getUintValue(string256(SPLIT_SIZE, index));
        Database(self).setAddressValue(string256x2(SPLIT_PARTIES, index, splitSize), receiver);
        Database(self).setUintValue(string256x2(SPLIT_FEES, index, splitSize), amount);
        Database(self).setUintValue(string256(SPLIT_SIZE, index), splitSize + 1);
    }

    function retrieveCashOut(address self, uint256 cashOutId) internal view returns (CashOut memory) {
        require(cashOutExists(self, cashOutId), "Cash out channel not exists");
        CashOut memory cashOut;
        cashOut.id = cashOutId;
        cashOut.kioskId = Database(self).getStringValue(keccak256(abi.encodePacked(KIOSK_ID, cashOutId)));
        cashOut.sessionId = Database(self).getUintValue(keccak256(abi.encodePacked(SESSION_ID, cashOutId)));
        cashOut.application = Database(self).getAddressValue(keccak256(abi.encodePacked(APPLICATION, cashOutId)));
        cashOut.status = Status(Database(self).getUintValue(keccak256(abi.encodePacked(STATUS, cashOutId))));
        cashOut.vaultLogicPercent = (Database(self).getUintValue(keccak256(abi.encodePacked(VL_FEE, cashOutId))));
        cashOut.vaultLogicAmount = (Database(self).getUintValue(keccak256(abi.encodePacked(VL_AMOUNT, cashOutId))));
        cashOut.withdrawAmount = Database(self).getUintValue(keccak256(abi.encodePacked(WITHDRAW_AMOUNT, cashOutId)));
        cashOut.reservedAmount = Database(self).getUintValue(keccak256(abi.encodePacked(RESERVED_AMOUNT, cashOutId)));
        cashOut.splitSize = Database(self).getUintValue(keccak256(abi.encodePacked(SPLIT_SIZE, cashOutId)));
        if (cashOut.splitSize == 0) return cashOut;
        cashOut.parties = new address[](cashOut.splitSize);
        cashOut.fees = new uint256[](cashOut.splitSize);
        for (uint256 i = 0; i < cashOut.splitSize; i++) {
            cashOut.parties[i] = Database(self).getAddressValue(keccak256(abi.encodePacked(SPLIT_PARTIES, cashOutId, i)));
            cashOut.fees[i] = Database(self).getUintValue(keccak256(abi.encodePacked(SPLIT_FEES, cashOutId, i)));
        }
        return cashOut;
    }

    function save(address self, string kioskId, uint256 sessionId, address application, uint256 status, uint256 vaultLogicPercent,
        uint256 vaultLogicAmount, uint256 withdrawAmount, uint256 reservedAmount, address[] parties, uint256[] fees)
    internal returns (uint256 index) {
        index = Database(self).getUintValue(CASH_OUT_INDEX);
        Database(self).setStringValue(keccak256(abi.encodePacked(KIOSK_ID, index)), kioskId);
        Database(self).setUintValue(keccak256(abi.encodePacked(SESSION_ID, index)), uint256(sessionId));
        Database(self).setAddressValue(keccak256(abi.encodePacked(APPLICATION, index)), application);
        Database(self).setUintValue(keccak256(abi.encodePacked(STATUS, index)), uint256(status));
        Database(self).setUintValue(keccak256(abi.encodePacked(VL_FEE, index)), vaultLogicPercent);
        Database(self).setUintValue(keccak256(abi.encodePacked(VL_AMOUNT, index)), vaultLogicAmount);
        Database(self).setUintValue(keccak256(abi.encodePacked(WITHDRAW_AMOUNT, index)), withdrawAmount);
        Database(self).setUintValue(keccak256(abi.encodePacked(RESERVED_AMOUNT, index)), reservedAmount);
        addSplits(self, index, parties, fees);
        Database(self).setBooleanValue(keccak256(abi.encodePacked(EXISTS, index)), true);
        Database(self).setUintValue(CASH_OUT_INDEX, index + 1);
    }

    /// @dev size of both arrays (e.g. parties and fees) stored in separate field 'splitSize'
    function get(address self, uint256 index) internal view returns (
        string kioskId,
        uint256 sessionId,
        address application,
        uint256 status,
        uint256 vaultLogicPercent,
        uint256 vaultLogicAmount,
        uint256 withdrawAmount,
        uint256 reservedAmount,
        uint256 splitSize
    ) {
        kioskId = Database(self).getStringValue(string256(KIOSK_ID, index));
        sessionId = Database(self).getUintValue(string256(SESSION_ID, index));
        application = Database(self).getAddressValue(string256(APPLICATION, index));
        status = Database(self).getUintValue(string256(STATUS, index));
        vaultLogicPercent = Database(self).getUintValue(string256(VL_FEE, index));
        vaultLogicAmount = Database(self).getUintValue(string256(VL_AMOUNT, index));
        withdrawAmount = Database(self).getUintValue(string256(WITHDRAW_AMOUNT, index));
        reservedAmount = Database(self).getUintValue(string256(RESERVED_AMOUNT, index));
        splitSize = Database(self).getUintValue(string256(SPLIT_SIZE, index));
    }

    *//*function retrieveCashOutKiosk(address self, uint256 index) internal view returns (KioskLib.Kiosk memory) {
        require(cashOutExists(self, index), "Cash out channel not exists");
        return self.retrieveKiosk(getKioskId(self, index));
    }*//*

    function setKioskId(address self, uint256 index, string kioskId) internal {
        Database(self).setStringValue(string256(KIOSK_ID, index), string(kioskId));
    }

    function getKioskId(address self, uint256 index) internal view returns (string) {
        return Database(self).getStringValue(string256(KIOSK_ID, index));
    }

    function setSessionId(address self, uint256 index, uint256 sessionId) internal {
        Database(self).setUintValue(string256(SESSION_ID, index), uint256(sessionId));
    }

    function getSessionId(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(string256(SESSION_ID, index));
    }

    function getApplication(address self, uint256 index) internal view returns (address) {
        return Database(self).getAddressValue(string256(APPLICATION, index));
    }

    function setStatus(address self, uint256 index, uint256 status) internal {
        Database(self).setUintValue(string256(STATUS, index), uint256(status));
    }

    function getStatus(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(string256(STATUS, index));
    }

    function setWithdrawAmount(address self, uint256 index, uint256 withdrawAmount) internal {
        Database(self).setUintValue(string256(WITHDRAW_AMOUNT, index), withdrawAmount);
    }

    function getWithdrawAmount(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(string256(WITHDRAW_AMOUNT, index));
    }

    function setReservedAmount(address self, uint256 index, uint256 reservedAmount) internal {
        Database(self).setUintValue(string256(RESERVED_AMOUNT, index), reservedAmount);
    }

    function getReservedAmount(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(string256(RESERVED_AMOUNT, index));
    }

    function setVaultLogicAmount(address self, uint256 index, uint256 vaultLogicAmount) internal {
        Database(self).setUintValue(string256(VL_AMOUNT, index), uint256(vaultLogicAmount));
    }

    function getVaultLogicAmount(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(string256(VL_AMOUNT, index));
    }

    function setVaultLogicPercent(address self, uint256 index, uint256 vaultLogicPercent) internal {
        Database(self).setUintValue(string256(VL_FEE, index), uint256(vaultLogicPercent));
    }

    function getVaultLogicPercent(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(string256(VL_FEE, index));
    }

    function getSplitSize(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(string256(SPLIT_SIZE, index));
    }

    function getSplit(address self, uint256 index, uint256 subIndex) internal view returns (address party, uint256 fee) {
        party = Database(self).getAddressValue(string256x2(SPLIT_PARTIES, index, subIndex));
        fee = Database(self).getUintValue(string256x2(SPLIT_FEES, index, subIndex));
    }

    function string256(string field, uint256 index) private pure returns (bytes32) {
        return keccak256(abi.encodePacked(field, index));
    }

    function string256x2(string field, uint256 index, uint256 counter) private pure returns (bytes32) {
        return keccak256(abi.encodePacked(field, index, counter));
    }

}*/

library ParameterLib {

    string constant VAULT_LOGIC_FEE_PERCENT = "vault_logic_fee_percent";

    function setVLFee(address _self, uint256 _vlFee) internal {
        Database(_self).setUintValue(keccak256(abi.encode(VAULT_LOGIC_FEE_PERCENT)), _vlFee);
    }

    function getVLFee(address self) internal view returns (uint256 _vlFee) {
        _vlFee = Database(self).getUintValue(keccak256(abi.encode(VAULT_LOGIC_FEE_PERCENT)));
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
