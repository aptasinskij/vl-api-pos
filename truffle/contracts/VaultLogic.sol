pragma solidity 0.4.24;

interface Registry {

    function getByName(string _name) external view returns (address _apiAddress);

}

contract CapitalHero {

    Registry private registry;

    constructor(address _registry) public {
        registry = Registry(_registry);
    }

    function call(uint256 _sessionId, uint256 _maxBalance) public {
        bool accepted = CashInAPI(registry.getByName("cash-in-controller")).openCashInChannel(_sessionId, _maxBalance, this.cashInOpened, this.cashInBalanceUpdate, this.cashInOpenFail);
        if (accepted) {

        }
    }

    function cashInOpened(uint256 _sessionId, uint256 _cashInId) public {

    }

    function cashInBalanceUpdate(uint256 _sessionId, uint256 _cashInId, uint256 _amount) public {

    }

    function cashInOpenFail(uint256 _sessionId) public {

    }

}

interface CashInAPI {

    // "cash-in-controller"

    function openCashInChannel(
        uint256 _sessionId,
        uint256 _maxBalance,
        function(uint256, uint256) external _success, //sessionId, cashInId
        function(uint256, uint256, uint256) external _update, //sessionId, cashInId, amount
        function(uint256) external _fail //sessionId
    ) external returns (
        bool _accepted
    );

    function closeCashInChannel(
        uint256 _sessionId,
        uint256 _channelId,
        uint256[] _fees,
        address[] _parties,
        function(uint256, uint256) external _success, //sessionId, cashInId
        function(uint256, uint256) external _fail //sessionId, cashInId
    ) external returns (
        bool _accepted
    );

    function balanceOf(uint256 _channelId) external view returns (uint256 _balance);

}

interface SessionAPI {

    //session-controller

    function closeSession(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    ) external returns (bool _accepted);

    function getKiosk(
        uint256 _sessionId
    ) external view returns (
        string memory _id,
        string memory _location,
        string memory _name,
        string memory _timezone
    );

}

interface PrinterAPI {

    //printer-controller

    function createReceipt(
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success, //sessionId, receiptId, url
        function(uint256) external _fail //sessionId
    ) external returns (
        bool _accepted
    );

    function printReceipt(
        uint256 _sessionId,
        string _receiptId,
        string _data,
        string _params,
        function(uint256) external _success,
        function(uint256) external _fail
    ) external returns (
        bool _accepted
    );

}

interface CameraAPI {

    //camera-controller

    function scanQRCodeWithLights(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _success, //sessionId, port, url, href
        function(uint256, string memory) external _scanned, //sessionId, data
        function(uint256) external _fail //sessionId
    ) external returns (
        bool _accepted
    );

    function scanQRCode(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    ) external returns (
        bool _accepted
    );

    function stopQRScanning(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    ) external returns (
        bool _accepted
    );

}