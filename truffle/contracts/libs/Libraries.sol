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

    // check existence
    function sessionExists(address self, uint256 sessionId) internal view returns (bool) {
        return Database(self).getBooleanValue(keccak256(abi.encodePacked(EXISTS, sessionId)));
    }

    // create
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

    function save(address self, uint256 sessionId, uint256 appId, string xToken, uint256 status, string kioskId) internal {
        require(!sessionExists(self, sessionId), "Session is already exists");
        require(self.kioskExists(kioskId), "Kiosk is not exists");
        require(self.applicationExists(appId), "Application is not exists");
        Database(self).setUintValue(keccak256(abi.encodePacked(APPLICATION_ID, sessionId)), appId);
        Database(self).setStringValue(keccak256(abi.encodePacked(X_TOKEN, sessionId)), xToken);
        Database(self).setUintValue(keccak256(abi.encodePacked(STATUS, sessionId)), status);
        Database(self).setBooleanValue(keccak256(abi.encodePacked(EXISTS, sessionId)), true);
    }

    function retrieveSession(address _self, uint256 _sessionId) internal view returns (Session memory _session) {
        require(sessionExists(_self, _sessionId), "Session is not exists");
        _session.id = _sessionId;
        _session.applicationId = Database(_self).getUintValue(keccak256(abi.encodePacked(APPLICATION_ID, _sessionId)));
        _session.kioskId = Database(_self).getStringValue(keccak256(abi.encodePacked(KIOSK_ID, _sessionId)));
        _session.xToken = Database(_self).getStringValue(keccak256(abi.encodePacked(X_TOKEN, _sessionId)));
        _session.status = Status(Database(_self).getUintValue(keccak256(abi.encodePacked(STATUS, _sessionId))));
        _session.hasActiveCashIn = Database(_self).getBooleanValue(keccak256(abi.encodePacked(HAS_ACTIVE_CASH_IN, _sessionId)));
        _session.hasActiveCashOut = Database(_self).getBooleanValue(keccak256(abi.encodePacked(HAS_ACTIVE_CASH_OUT, _sessionId)));
    }

    function sessionIsNotActive(address _self, uint256 _sessionId) internal view returns (bool _isNotActive) {
        _isNotActive = Status(Database(_self).getUintValue(keccak256(abi.encodePacked(STATUS, _sessionId)))) != Status.ACTIVE;
    }

    function sessionNotBelongsToApplication(address _self, uint256 _sessionId, address _application) internal view returns (bool) {
        return retrieveSessionApplicationDeployedAddress(_self, _sessionId) != _application;
    }

    function sessionHasActiveCashIn(address _self, uint256 _sessionId) internal view returns (bool _hasActiveCashIn) {
        _hasActiveCashIn = Database(_self).getBooleanValue(keccak256(abi.encodePacked(HAS_ACTIVE_CASH_IN, _sessionId)));
    }

    function retrieveSessionApplication(address self, uint256 sessionId) internal view returns (ApplicationLib.Application memory) {
        require(sessionExists(self, sessionId), "Session is not exists");
        return self.retrieveApplication(Database(self).getUintValue(keccak256(abi.encodePacked(APPLICATION_ID, sessionId))));
    }

    function retrieveSessionKiosk(address self, uint256 sessionId) internal view returns (KioskLib.Kiosk memory) {
        require(sessionExists(self, sessionId), "Session is not exists");
        return self.retrieveKiosk(Database(self).getStringValue(keccak256(abi.encodePacked(KIOSK_ID, sessionId))));
    }

    function retrieveSessionApplicationDeployedAddress(address self, uint256 _sessionId) internal view returns (address) {
        require(sessionExists(self, _sessionId), "Session is not exists");
        return self.getAddress(getAppId(self, _sessionId));
    }

    function get(address self, uint256 index) internal view returns (uint256 appId, string xToken, uint256 status) {
        require(sessionExists(self, index), "Session is not exists");
        appId = Database(self).getUintValue(keccak256(abi.encodePacked(APPLICATION_ID, index)));
        xToken = Database(self).getStringValue(keccak256(abi.encodePacked(X_TOKEN, index)));
        status = Database(self).getUintValue(keccak256(abi.encodePacked(STATUS, index)));
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

    function setSessionHasActiveCashIn(address _self, uint256 _sessionId, bool _flag) internal {
        require(sessionExists(_self, _sessionId), "Session is not exists");
        Database(_self).setBooleanValue(keccak256(abi.encodePacked(HAS_ACTIVE_CASH_IN, _sessionId)), _flag);
    }

    function setStatus(address self, uint256 index, Status status) internal {
        Database(self).setUintValue(keccak256(abi.encodePacked(STATUS, index)), uint256(status));
    }

    function sessionIsActive(address self, uint256 _sessionId) internal view returns (bool _active) {
        uint256 statusIndex = Database(self).getUintValue(keccak256(abi.encodePacked(STATUS, _sessionId)));
        _active = Status(statusIndex) == Status.ACTIVE;
    }

}

library CashInLib {

    bytes32 constant CASH_IN_ID = keccak256(abi.encode("CashInIndex"));
    string constant SESSION_ID = "cash-in.session.id:uint256";
    string constant APPLICATION = "cash-in.application.address:address";
    string constant STATUS = "cash-in.status:uint256";

    string constant SPLIT_SIZE = "cash-in.split.size:uint256";
    string constant SPLIT_FEE = "cash-in.split.fee:uint256";
    string constant SPLIT_PARTY = "cash-in.split.party:address";

    string constant ACCOUNT_BALANCE = "cash-in.account.balance:uint256";
    string constant ACCONT_MAX_BALANCE = "cash-in.account.max-balance:uint256";
    string constant ACCOUNT_FEE_PERCENT = "cash-in.account.fee-percent:uint256";
    string constant ACCOUNT_UPDATE = "cash-in.account.update:function";

    string constant CLOSE_SESSION_ID = "cash-in.close.session.id:uint256";
    string constant CLOSE_SUCCESS = "cash-in.close.success:function";
    string constant CLOSE_FAIL = "cash-in.close.fail:function";

    string constant OPEN_SESSION_ID = "cash-in.open.session.id:uint256";
    string constant OPEN_MAX_BALANCE = "cash-in.open.max-amount:uint256";
    string constant OPEN_SUCCESS = "cash-in.open.success:function";
    string constant OPEN_UPDATE = "cash-in.open.update:function";
    string constant OPEN_FAIL = "cash-in.open.fail:function";

    enum Status {
        CREATING,
        ACTIVE,
        FAILED_TO_CREATE,
        CLOSE_REQUESTED,
        CLOSED,
        FAILED_TO_CLOSE
    }

    struct Open {
        uint256 sessionId;
        uint256 maxBalance;
        function(uint256, uint256) external success;
        function(uint256, uint256, uint256) external update;
        function(uint256) external fail;
    }

    struct CashIn {
        uint256 id;
        uint256 sessionId;
        address application;
        Status status;
    }

    struct Account {
        uint256 balance;
        uint256 maxBalance;
        uint256 fee;
        function(uint256, uint256, uint256) external update;
    }

    struct Close {
        uint256 sessionId;
        function(uint256, uint256) external success;
        function(uint256, uint256) external fail;
    }

    struct Split {
        uint256 size;
        uint256[] fees;
        address[] parties;
    }

    function createCashIn(address _self, uint256 _sessionId, address _application) internal returns (uint256 _cashInId) {
        _cashInId = Database(_self).getUintValue(CASH_IN_ID);
        Database(_self).setUintValue(keccak256(abi.encodePacked(SESSION_ID, _cashInId)), _sessionId);
        Database(_self).setAddressValue(keccak256(abi.encodePacked(APPLICATION, _cashInId)), _application);
        Database(_self).setUintValue(CASH_IN_ID, _cashInId + 1);
    }

    function retrieveCashIn(address _self, uint256 _cashInId) internal view returns (CashIn memory _cashIn) {
        _cashIn.id = _cashInId;
        _cashIn.sessionId = Database(_self).getUintValue(keccak256(abi.encodePacked(SESSION_ID, _cashInId)));
        _cashIn.application = Database(_self).getAddressValue(keccak256(abi.encodePacked(APPLICATION, _cashInId)));
        _cashIn.status = Status(Database(_self).getUintValue(keccak256(abi.encodePacked(STATUS, _cashInId))));
    }

    function cashInIsCreating(address _self, uint256 _cashInId) internal view returns (bool _isCreating) {
        _isCreating = Status(Database(_self).getUintValue(keccak256(abi.encodePacked(STATUS, _cashInId)))) == Status.CREATING;
    }

    function cashInIsActive(address _self, uint256 _cashInId) internal view returns (bool _isActive) {
        _isActive = Status(Database(_self).getUintValue(keccak256(abi.encodePacked(STATUS, _cashInId)))) == Status.ACTIVE;
    }

    function cashInInStatus(address _self, uint256 _cashInId, Status _status) internal view returns (bool _isInStatus) {
        _isInStatus = Status(Database(_self).getUintValue(keccak256(abi.encodePacked(STATUS, _cashInId)))) == _status;
    }

    function setCashInStatus(address _self, uint256 _cashInId, Status _status) internal {
        Database(_self).setUintValue(keccak256(abi.encodePacked(STATUS, _cashInId)), uint256(_status));
    }

    function createCashInSplit(
        address _self,
        uint256 _cashInId,
        uint256[] _fees,
        address[] _parties
    )
        internal
    {
        Database(_self).setUintValue(keccak256(abi.encodePacked(SPLIT_SIZE, _cashInId)), _fees.length);
        for (uint256 i = 0; i < _fees.length; i++) {
            Database(_self).setUintValue(keccak256(abi.encodePacked(SPLIT_FEE, _cashInId, i)), _fees[i]);
            Database(_self).setAddressValue(keccak256(abi.encodePacked(SPLIT_PARTY, _cashInId, i)), _parties[i]);
        }
    }

    function retrieveCashInSplit(address _self, uint256 _cashInId) internal view returns (Split memory _split) {
        _split.size = Database(_self).getUintValue(keccak256(abi.encodePacked(SPLIT_SIZE, _cashInId)));
        for (uint256 i = 0; i < _split.size; i++) {
            _split.fees[i] = Database(_self).getUintValue(keccak256(abi.encodePacked(SPLIT_FEE, _cashInId, i)));
            _split.parties[i] = Database(_self).getAddressValue(keccak256(abi.encodePacked(SPLIT_PARTY, _cashInId, i)));
        }
    }

    function createCashInAccount(
        address _self,
        uint256 _cashInId,
        uint256 _maxBalance,
        uint256 _fee,
        function(uint256, uint256, uint256) external _update
    )
        internal
    {
        Database(_self).setUintValue(keccak256(abi.encodePacked(ACCONT_MAX_BALANCE, _cashInId)), _maxBalance);
        Database(_self).setUintValue(keccak256(abi.encodePacked(ACCOUNT_FEE_PERCENT, _cashInId)), _fee);
        Database(_self).setUint256X3Function(keccak256(abi.encodePacked(ACCOUNT_UPDATE, _cashInId)), _update);
    }

    function retrieveCashInAccount(address _self, uint256 _cashInId) internal view returns (Account memory _account) {
        _account.balance = Database(_self).getUintValue(keccak256(abi.encodePacked(ACCOUNT_BALANCE, _cashInId)));
        _account.maxBalance = Database(_self).getUintValue(keccak256(abi.encodePacked(ACCONT_MAX_BALANCE, _cashInId)));
        _account.fee = Database(_self).getUintValue(keccak256(abi.encodePacked(ACCOUNT_FEE_PERCENT, _cashInId)));
        _account.update = Database(_self).getUint256X3Function(keccak256(abi.encodePacked(ACCOUNT_UPDATE, _cashInId)));
    }

    function setCashInAccountBalance(address _self, uint256 _cashInId, uint256 _balance) internal {
        Database(_self).setUintValue(keccak256(abi.encodePacked(ACCOUNT_BALANCE, _cashInId)), _balance);
    }

    function createCashInClose(
        address _self,
        uint256 _cashInId,
        uint256 _sessionId,
        function(uint256, uint256) external _success,
        function(uint256, uint256) external _fail
    )
        internal
    {
        Database(_self).setUintValue(keccak256(abi.encodePacked(CLOSE_SESSION_ID, _cashInId)), _sessionId);
        Database(_self).setUint256X2Function(keccak256(abi.encodePacked(CLOSE_SUCCESS, _cashInId)), _success);
        Database(_self).setUint256X2Function(keccak256(abi.encodePacked(CLOSE_FAIL, _cashInId)), _fail);
    }

    function retrieveCashInClose(address _self, uint256 _cashInId) internal view returns (Close memory _close) {
        _close.sessionId = Database(_self).getUintValue(keccak256(abi.encodePacked(CLOSE_SESSION_ID, _cashInId)));
        _close.success = Database(_self).getUint256X2Function(keccak256(abi.encodePacked(CLOSE_SUCCESS, _cashInId)));
        _close.fail = Database(_self).getUint256X2Function(keccak256(abi.encodePacked(CLOSE_FAIL, _cashInId)));
    }

    function createCashInOpen(
        address _self,
        uint256 _cashInId,
        uint256 _sessionId,
        uint256 _maxBalance,
        function(uint256, uint256) external _success,
        function(uint256, uint256, uint256) external _update,
        function(uint256) external _fail
    )
        internal
    {
        Database(_self).setUintValue(keccak256(abi.encodePacked(OPEN_SESSION_ID, _cashInId)), _sessionId);
        Database(_self).setUintValue(keccak256(abi.encodePacked(OPEN_MAX_BALANCE, _cashInId)), _maxBalance);
        Database(_self).setUint256X2Function(keccak256(abi.encodePacked(OPEN_SUCCESS, _cashInId)), _success);
        Database(_self).setUint256X3Function(keccak256(abi.encodePacked(OPEN_UPDATE, _cashInId)), _update);
        Database(_self).setUint256Function(keccak256(abi.encodePacked(OPEN_FAIL, _cashInId)), _fail);
    }

    function retrieveCashInOpen(address _self, uint256 _cashInId) internal view returns (Open memory _open) {
        _open.sessionId = Database(_self).getUintValue(keccak256(abi.encodePacked(OPEN_SESSION_ID, _cashInId)));
        _open.maxBalance = Database(_self).getUintValue(keccak256(abi.encodePacked(OPEN_MAX_BALANCE, _cashInId)));
        _open.success = Database(_self).getUint256X2Function(keccak256(abi.encodePacked(OPEN_SUCCESS, _cashInId)));
        _open.update = Database(_self).getUint256X3Function(keccak256(abi.encodePacked(OPEN_UPDATE, _cashInId)));
        _open.fail = Database(_self).getUint256Function(keccak256(abi.encodePacked(OPEN_FAIL, _cashInId)));
    }

}

library CashOutLib {

    using ApplicationLib for address;
    using KioskLib for address;

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

    function retrieveCashOutKiosk(address self, uint256 index) internal view returns (KioskLib.Kiosk memory) {
        require(cashOutExists(self, index), "Cash out channel not exists");
        return self.retrieveKiosk(getKioskId(self, index));
    }

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

}

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

library PrinterLib {

    //ReceiptCreate 'headers'
    bytes32 constant RECEIPT_CREATE_ID = keccak256(abi.encode("ReceiptCreate:uint256"));
    string constant RECEIPT_CREATE_EXISTS = "receipt-create.exists:boolean";
    string constant RECEIPT_CREATE_SESSION_ID = "receipt-create.session.id:uint256";
    string constant RECEIPT_CREATE_SUCCESS = "receipt-create.success:function";
    string constant RECEIPT_CREATE_FAIL = "receipt-create.fail:function";

    //ReceiptPrint 'headers'
    bytes32 constant RECEIPT_PRINT_ID = keccak256(abi.encode("ReceiptPrint:uint256"));
    string constant RECEIPT_PRINT_EXISTS = "receipt-print.exists:boolean";
    string constant RECEIPT_PRINT_SESSION_ID = "receipt-print.session.id:uint256";
    string constant RECEIPT_PRINT_RECEIPT_ID = "receipt-print.receipt_id:string";
    string constant RECEIPT_PRINT_DATA = "receipt-print.data:string";
    string constant RECEIPT_PRINT_PARAMS = "receipt-print.params:string";
    string constant RECEIPT_PRINT_SUCCESS = "receipt-print.success:function";
    string constant RECEIPT_PRINT_FAIL = "receipt-print.fail:function";

    struct ReceiptCreate {
        uint256 id;
        uint256 sessionId;
        function(uint256, string memory, string memory) external success;
        function(uint256) external fail;
    }

    struct ReceiptPrint {
        uint256 id;
        uint256 sessionId;
        string receiptId;
        string data;
        string params;
        function(uint256) external success;
        function(uint256) external fail;
    }

    function receiptCreateExists(address _self, uint256 _id) internal view returns (bool _exists) {
        _exists = Database(_self).getBooleanValue(keccak256(abi.encodePacked(RECEIPT_CREATE_EXISTS, _id)));
    }

    function receiptPrintExists(address _self, uint256 _id) internal view returns (bool _exists) {
        _exists = Database(_self).getBooleanValue(keccak256(abi.encodePacked(RECEIPT_PRINT_EXISTS, _id)));
    }

    function getNextReceiptCreateId(address _self) internal view returns (uint256 _id) {
        _id = Database(_self).getUintValue(RECEIPT_CREATE_ID);
    }

    function getNextReceiptPrintId(address _self) internal view returns (uint256 _id) {
        _id = Database(_self).getUintValue(RECEIPT_PRINT_ID);
    }

    function createReceiptCreate(address _self, ReceiptCreate memory _command) internal {
        require(!receiptCreateExists(_self, _command.id), "receipt create already exists");
        require(getNextReceiptCreateId(_self) == _command.id, "violation of receipt create id sequence");
        Database(_self).setBooleanValue(keccak256(abi.encodePacked(RECEIPT_CREATE_EXISTS, _command.id)), true);
        Database(_self).setUintValue(keccak256(abi.encodePacked(RECEIPT_CREATE_SESSION_ID, _command.id)), _command.sessionId);
        Database(_self).setUint256X1StringX2Function(keccak256(abi.encodePacked(RECEIPT_CREATE_SUCCESS, _command.id)), _command.success);
        Database(_self).setUint256Function(keccak256(abi.encodePacked(RECEIPT_CREATE_FAIL, _command.id)), _command.fail);
        Database(_self).setUintValue(RECEIPT_CREATE_ID, _command.id + 1);
    }

    function retrieveReceiptCreate(address _self, uint256 _id) internal view returns (ReceiptCreate memory) {
        require(receiptCreateExists(_self, _id), "receipt create is not exists");
        // @formatter:off
        return ReceiptCreate({
            id: _id,
            sessionId: Database(_self).getUintValue(keccak256(abi.encodePacked(RECEIPT_CREATE_SESSION_ID, _id))),
            success: Database(_self).getUint256X1StringX2Function(keccak256(abi.encodePacked(RECEIPT_CREATE_SUCCESS, _id))),
            fail: Database(_self).getUint256Function(keccak256(abi.encodePacked(RECEIPT_CREATE_FAIL, _id)))
        });
        // @formatter:on
    }

    function createReceiptPrint(address _self, ReceiptPrint memory _command) internal {
        require(!receiptPrintExists(_self, _command.id), "receipt print already exists");
        require(getNextReceiptPrintId(_self) == _command.id, "violation of receipt print id sequence");
        Database(_self).setBooleanValue(keccak256(abi.encodePacked(RECEIPT_PRINT_EXISTS, _command.id)), true);
        Database(_self).setUintValue(keccak256(abi.encodePacked(RECEIPT_PRINT_SESSION_ID, _command.id)), _command.sessionId);
        Database(_self).setStringValue(keccak256(abi.encodePacked(RECEIPT_PRINT_RECEIPT_ID, _command.id)), _command.receiptId);
        Database(_self).setStringValue(keccak256(abi.encodePacked(RECEIPT_PRINT_DATA, _command.id)), _command.data);
        Database(_self).setStringValue(keccak256(abi.encodePacked(RECEIPT_PRINT_PARAMS, _command.id)), _command.params);
        Database(_self).setUint256Function(keccak256(abi.encodePacked(RECEIPT_PRINT_SUCCESS, _command.id)), _command.success);
        Database(_self).setUint256Function(keccak256(abi.encodePacked(RECEIPT_PRINT_FAIL, _command.id)), _command.fail);
        Database(_self).setUintValue(RECEIPT_PRINT_ID, _command.id + 1);
    }

    function retrieveReceiptPrint(address _self, uint256 _id) internal view returns (ReceiptPrint memory) {
        require(receiptPrintExists(_self, _id), "receipt print is not exists");
        // @formatter:off
        return ReceiptPrint({
            id: _id,
            sessionId: Database(_self).getUintValue(keccak256(abi.encodePacked(RECEIPT_PRINT_SESSION_ID, _id))),
            receiptId: Database(_self).getStringValue(keccak256(abi.encodePacked(RECEIPT_PRINT_RECEIPT_ID, _id))),
            data: Database(_self).getStringValue(keccak256(abi.encodePacked(RECEIPT_PRINT_DATA, _id))),
            params: Database(_self).getStringValue(keccak256(abi.encodePacked(RECEIPT_PRINT_PARAMS, _id))),
            success: Database(_self).getUint256Function(keccak256(abi.encodePacked(RECEIPT_PRINT_SUCCESS, _id))),
            fail: Database(_self).getUint256Function(keccak256(abi.encodePacked(RECEIPT_PRINT_FAIL, _id)))
        });
        // @formatter:on
    }

}