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

library KioskLib {

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

    function kioskExists(address self, string memory kioskId) internal view returns (bool) {
        return Database(self).getBooleanValue(keccak256(abi.encodePacked(EXISTS, kioskId)));
    }

    function createKiosk(address self, Kiosk memory kiosk) internal returns (bool) {
        require(!kioskExists(self, kiosk.id), "Kiosk already exists");
        Database(self).setStringValue(keccak256(abi.encode(ID, kiosk.id)), kiosk.id);
        Database(self).setStringValue(keccak256(abi.encode(LOCATION, kiosk.id)), kiosk.location);
        Database(self).setStringValue(keccak256(abi.encode(NAME, kiosk.id)), kiosk.name);
        Database(self).setStringValue(keccak256(abi.encode(TIME_ZONE, kiosk.id)), kiosk.timezone);
        Database(self).setBooleanValue(keccak256(abi.encodePacked(EXISTS, kiosk.id)), true);
        return true;
    }

    function retrieveKiosk(address self, string memory kioskId) internal view returns (Kiosk memory) {
        require(kioskExists(self, kioskId), "Kiosk is not exists");
        // @formatter:off
        return Kiosk({
            id: kioskId,
            location: Database(self).getStringValue(keccak256(abi.encode(LOCATION, kioskId))),
            name: Database(self).getStringValue(keccak256(abi.encode(NAME, kioskId))),
            timezone: Database(self).getStringValue(keccak256(abi.encode(TIME_ZONE, kioskId)))
        });
        // @formatter:on
    }

}

library SessionLib {

    using ApplicationLib for address;
    using KioskLib for address;

    string constant EXISTS = "session.exists";

    string constant ID = "session_id";
    string constant APPLICATION_ID = "session_application_id";
    string constant KIOSK_ID = "session_kiosk_id";
    string constant X_TOKEN = "session_x_token";
    string constant STATUS = "session_status";
    string constant HAS_ACTIVE_CASH_IN = "session_has_active_cash_in";
    string constant HAS_ACTIVE_CASH_OUT = "session_has_active_cash_out";

    enum Status {CREATING, ACTIVE, FAILED_TO_CREATE, CLOSE_REQUESTED, CLOSED}

    struct Session {
        uint256 id;
        uint256 applicationId;
        string kioskId;
        string xToken;
        Status status;
        bool hasActiveCashIn;
        bool hasActiveCashOut;
    }

    function sessionExists(address self, uint256 sessionId) internal view returns (bool) {
        return Database(self).getBooleanValue(keccak256(abi.encodePacked(EXISTS, sessionId)));
    }

    function createSession(address self, Session memory session) internal returns (bool) {
        require(!sessionExists(self, session.id), "Session is already exists");
        require(self.kioskExists(session.kioskId), "Kiosk is not exists");
        require(self.applicationExists(session.applicationId), "Application is not exists");
        Database(self).setUintValue(keccak256(abi.encodePacked(ID, session.id)), session.id);
        Database(self).setUintValue(keccak256(abi.encodePacked(APPLICATION_ID, session.id)), session.applicationId);
        Database(self).setStringValue(keccak256(abi.encodePacked(KIOSK_ID, session.id)), session.kioskId);
        Database(self).setStringValue(keccak256(abi.encodePacked(X_TOKEN, session.id)), session.xToken);
        Database(self).setUintValue(keccak256(abi.encodePacked(STATUS, session.id)), uint256(session.status));
        Database(self).setBooleanValue(keccak256(abi.encodePacked(EXISTS, session.id)), true);
        return true;
    }

    function retrieveSession(address self, uint256 sessionId) internal view returns (Session memory) {
        require(sessionExists(self, sessionId), "Not exists");
        // @formatter:off
        return Session({
            id : sessionId,
            applicationId : Database(self).getUintValue(keccak256(abi.encodePacked(APPLICATION_ID, sessionId))),
            kioskId : Database(self).getStringValue(keccak256(abi.encodePacked(KIOSK_ID, sessionId))),
            xToken : Database(self).getStringValue(keccak256(abi.encodePacked(X_TOKEN, sessionId))),
            status : Status(Database(self).getUintValue(keccak256(abi.encodePacked(STATUS, sessionId)))),
            hasActiveCashIn : Database(self).getBooleanValue(keccak256(abi.encodePacked(HAS_ACTIVE_CASH_IN, sessionId))),
            hasActiveCashOut : Database(self).getBooleanValue(keccak256(abi.encodePacked(HAS_ACTIVE_CASH_OUT, sessionId)))
        });
        // @formatter:on
    }

    function retrieveSessionApplication(address self, uint256 sessionId) internal view returns (ApplicationLib.Application memory) {
        require(sessionExists(self, sessionId), "Not Exists");
        return self.retrieveApplication(Database(self).getUintValue(keccak256(abi.encodePacked(APPLICATION_ID, sessionId))));
    }

    function retrieveSessionKiosk(address self, uint256 sessionId) internal view returns (KioskLib.Kiosk memory) {
        require(sessionExists(self, sessionId), "Not Exists");
        return self.retrieveKiosk(Database(self).getStringValue(keccak256(abi.encodePacked(KIOSK_ID, sessionId))));
    }

    function retrieveSessionApplicationDeployedAddress(address self, uint256 _sessionId) internal view returns (address) {
        require(sessionExists(self, _sessionId), "Session Not Exists");
        return self.getAddress(getAppId(self, _sessionId));
    }

    function save(address self, uint256 sessionId, uint256 appId, string xToken, uint256 status, string kioskId) internal {
        require(!sessionExists(self, sessionId), "Session is already exists");
        require(self.kioskExists(kioskId), "Kiosk is not exists");
        require(self.applicationExists(appId), "Application is not exists");
        Database(self).setUintValue(keccak256(abi.encodePacked(APPLICATION_ID, sessionId)), appId);
        Database(self).setStringValue(keccak256(abi.encodePacked(X_TOKEN, sessionId)), xToken);
        Database(self).setUintValue(keccak256(abi.encodePacked(STATUS, sessionId)), status);
        Database(self).setBooleanValue(keccak256(abi.encodePacked(EXISTS, sessionId)), true);
    }

    function get(address self, uint256 index) internal view returns (uint256 appId, string xToken, uint256 status) {
        appId = Database(self).getUintValue(keccak256(abi.encodePacked(APPLICATION_ID, index)));
        xToken = Database(self).getStringValue(keccak256(abi.encodePacked(X_TOKEN, index)));
        status = Database(self).getUintValue(keccak256(abi.encodePacked(STATUS, index)));
    }

    function setHasActiveCashIn(address _self, uint256 _sessionId, bool _flag) internal {
        Database(_self).setBooleanValue(keccak256(abi.encodePacked(HAS_ACTIVE_CASH_IN, _sessionId)), _flag);
    }

    function getIsHasActiveCashIn(address _self, uint256 _sessionId) internal view returns (bool) {
        return Database(_self).getBooleanValue(keccak256(abi.encodePacked(HAS_ACTIVE_CASH_IN, _sessionId)));
    }

    function getAppId(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(keccak256(abi.encodePacked(APPLICATION_ID, index)));
    }

    function getXToken(address self, uint256 index) internal view returns (string) {
        return Database(self).getStringValue(keccak256(abi.encodePacked(X_TOKEN, index)));
    }

    function getStatus(address self, uint256 index) internal view returns (Status) {
        return Status(Database(self).getUintValue(keccak256(abi.encodePacked(STATUS, index))));
    }

    function setStatus(address self, uint256 index, Status status) internal {
        Database(self).setUintValue(keccak256(abi.encodePacked(STATUS, index)), uint256(status));
    }

}

library CashInLib {

    bytes32 constant CASH_IN_INDEX = keccak256(abi.encode("CashInIndex"));

    string constant EXISTS = "cash-in.exists";
    string constant SESSION_ID = "cash-in.session.id";
    string constant APPLICATION = "cash-in.application.address";
    string constant STATUS = "cash-in.status";
    string constant BALANCE = "cash-in.balance";
    string constant VL_FEE = "cash-in.vault_logic_fee_percent";
    string constant VL_BALANCE = "cash-in.vault_logic_balance";
    string constant APP_BALANCE = "cash-in.application_balance";

    ///@dev set of constants to store and retrieve dynamic sized arrays of splits
    string constant SPLIT_SIZE = "cash_in_split_size";
    string constant SPLIT_PARTIES = "cash_in_split_parties";
    string constant SPLIT_FEES = "cash_in_split_fees";

    enum Status {CREATING, ACTIVE, FAILED_TO_CREATE, CLOSE_REQUESTED, CLOSED, FAILED_TO_CLOSE}

    struct CashIn {
        uint256 id;
        uint256 sessionId;
        address application;
        Status status;
        uint256 balance;
        uint256 vaultLogicPercent;
        uint256 vaultLogicBalance;
        uint256 applicationBalance;
        uint256 splitSize;
        address[] parties;
        uint256[] fees;
    }

    function getCounter(address self) internal view returns (uint256) {
        Database(self).getUintValue(CASH_IN_INDEX);
    }

    function cashInExists(address self, uint256 cashInId) internal view returns (bool) {
        return Database(self).getBooleanValue(keccak256(abi.encodePacked(EXISTS, cashInId)));
    }

    function createCashIn(address self, CashIn memory cashIn) internal returns (uint256 index) {
        index = Database(self).getUintValue(CASH_IN_INDEX);
        Database(self).setUintValue(keccak256(abi.encodePacked(SESSION_ID, index)), cashIn.sessionId);
        Database(self).setAddressValue(keccak256(abi.encodePacked(APPLICATION, index)), cashIn.application);
        Database(self).setUintValue(keccak256(abi.encodePacked(STATUS, index)), uint256(cashIn.status));
        Database(self).setUintValue(keccak256(abi.encodePacked(VL_FEE, index)), cashIn.vaultLogicPercent);
        Database(self).setBooleanValue(keccak256(abi.encodePacked(EXISTS, index)), true);
        Database(self).setUintValue(CASH_IN_INDEX, index + 1);
    }

    function retrieveCashIn(address self, uint256 cashInId) internal view returns (CashIn memory) {
        require(cashInExists(self, cashInId), "Not exists");
        CashIn memory cashIn;
        cashIn.id = cashInId;
        cashIn.sessionId = Database(self).getUintValue(keccak256(abi.encodePacked(SESSION_ID, cashInId)));
        cashIn.application = Database(self).getAddressValue(keccak256(abi.encodePacked(APPLICATION, cashInId)));
        cashIn.status = Status(Database(self).getUintValue(keccak256(abi.encodePacked(STATUS, cashInId))));
        cashIn.balance = Database(self).getUintValue(keccak256(abi.encodePacked(BALANCE, cashInId)));
        cashIn.vaultLogicPercent = Database(self).getUintValue(keccak256(abi.encodePacked(VL_FEE, cashInId)));
        cashIn.vaultLogicBalance = Database(self).getUintValue(keccak256(abi.encodePacked(VL_BALANCE, cashInId)));
        cashIn.applicationBalance = Database(self).getUintValue(keccak256(abi.encodePacked(APP_BALANCE, cashInId)));
        cashIn.splitSize = Database(self).getUintValue(keccak256(abi.encodePacked(SPLIT_SIZE, cashInId)));
        if (cashIn.splitSize == 0) return cashIn;
        cashIn.parties = new address[](cashIn.splitSize);
        cashIn.fees = new uint256[](cashIn.splitSize);
        for (uint256 i = 0; i < cashIn.splitSize; i++) {
            cashIn.parties[i] = Database(self).getAddressValue(keccak256(abi.encodePacked(SPLIT_PARTIES, cashInId, i)));
            cashIn.fees[i] = Database(self).getUintValue(keccak256(abi.encodePacked(SPLIT_FEES, cashInId, i)));
        }
        return cashIn;
    }

    function save(address self, uint256 sessionId, address application, uint256 status) internal returns (uint256 index) {
        index = Database(self).getUintValue(CASH_IN_INDEX);
        Database(self).setUintValue(string256(SESSION_ID, index), sessionId);
        Database(self).setAddressValue(string256(APPLICATION, index), application);
        Database(self).setUintValue(string256(STATUS, index), status);
        Database(self).setUintValue(CASH_IN_INDEX, index + 1);
    }

    /// @dev size of both arrays (e.g. parties and fees) stored in separate field 'splitSize'
    function get(address self, uint256 index) internal view returns (
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

    function getSessionId(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(string256(SESSION_ID, index));
    }

    function getApplication(address self, uint256 index) internal view returns (address) {
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

    function getVLFee(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(keccak256(abi.encodePacked(VL_FEE, index)));
    }

    function setApplicationBalance(address self, uint256 index, uint256 balance) internal {
        Database(self).setUintValue(string256(APP_BALANCE, index), balance);
    }

    function getApplicationBalance(address self, uint256 index) internal view returns (uint256) {
        return Database(self).getUintValue(string256(APP_BALANCE, index));
    }

    function setStatus(address self, uint256 index, uint256 status) internal {
        Database(self).setUintValue(string256(STATUS, index), uint256(status));
    }

    function getStatus(address self, uint256 index) internal view returns (uint256) {
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

library TokenLib {

    string constant TOKEN_AMOUNT = "token_amount";

    function set(address self, address customer, uint256 amount) internal {
        Database(self).setUintValue(keccak256(abi.encodePacked(TOKEN_AMOUNT, customer)), amount);
    }

    function get(address self, address customer) internal view returns (uint256) {
        return Database(self).getUintValue(keccak256(abi.encodePacked(TOKEN_AMOUNT, customer)));
    }

}