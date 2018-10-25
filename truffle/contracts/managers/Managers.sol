pragma solidity 0.4.24;

contract ACashChannelsManager {

    function openCashInChannel(
        address _application,
        uint256 _sessionId,
        uint256 _maxBalance,
        function(uint256, uint256) external _success,
        function(uint256, uint256, uint256) external _update,
        function(uint256) external _fail
    )
        public
        returns (bool _accepted);

    function closeCashInChannel(
        address _application,
        uint256 _sessionId,
        uint256 _channelId,
        uint256[] _fees,
        address[] _parties,
        function(uint256, uint256) external _success,
        function(uint256, uint256) external _fail
    )
        public
        returns (bool _accepted);

    function confirmOpen(uint256 channelId) public;

    function confirmClose(uint256 channelId) public;

    function updateCashInBalance(uint256 channelId, uint256 amount) public;

    function balanceOf(address _application, uint256 _channelId) public view returns (uint256);

}

contract AnApplicationManager {

    enum ApplicationStatus { PENDING, ENABLED, DISABLED }

    function registerApplication(uint256 appId, string name, address owner, string url, address appAddr) public;

    function enableApplication(uint256 appId) public;

}

contract AParameterManager {

    function setVLFee(uint256 _vlFee) public;

    function getVLFee() public view returns (uint256 _vlFee);

}

contract ASessionManager {

    function createSession(uint256 _sessionId, uint256 _appId, string _xToken, string _kioskId) public;

    function closeSession(uint256 sessionId) public;

    function confirmClose(uint256 sessionId) public;

    function isActive(uint256 sessionId) public view returns(bool);

    function isHasActiveCashIn(uint256 _sessionId) public view returns(bool);

    function activate(uint256 _sessionId) public;

    function validateCanOpenCashIn(uint256 _sessionId, address _application) public view returns (bool _canOpenCashIn);

    function setSessionHasActiveCashIn(uint256 _sessionId) public;

    function setSessionIsNotHasActiveCashIn(uint256 _sessionId) public;

}

contract ATokenManager {

    event Transfer(address indexed to, uint value);

    event TransferFrom(address indexed from, address indexed to, uint value);

    function balanceOf(address _owner) public view returns (uint256);

    function transfer(address _recipient, uint _value) public;

    function transferFrom(address _from, address _to, uint _value) public;

}

contract ACameraManager {

    function scanQRCode(
        address _application,
        uint256 _sessionId,
        bool _lights,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    ) 
        public 
        returns (bool _accepted);

    function confirmFailStart(uint256 _commandId) public;

    function confirmStart(uint256 _commandId, string memory _port, string memory _url, string memory _href) public;

    function confirmScanned(uint256 _sessionId, string memory _qr) public;

    function stopQRScanning(
        address _application,
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    ) 
        public 
        returns (bool _accepted);

    function confirmStop(uint256 _commandId) public;

    function confirmFailStop(uint256 _commandId) public;

}

contract APrinterManager {

    function createReceipt(
        address _application,
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success,
        function(uint256) external _fail
    )
    public
    returns (bool _accepted);

    function confirmCreate(uint256 _commandId, string memory _receiptId, string memory _url) public;

    function confirmFailCreate(uint256 _commandId) public;

    function printReceipt(
        address _application,
        uint256 _sessionId,
        string memory _receiptId,
        string memory _data,
        string memory _params,
        function(uint256) external _success,
        function(uint256) external _fail
    )
    public
    returns (bool _accepted);

    function confirmPrint(uint256 _commandId) public;

    function confirmFailPrint(uint256 _commandId) public;

}