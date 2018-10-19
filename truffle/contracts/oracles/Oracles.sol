pragma solidity 0.4.24;

contract AnApplicationOracle {

    event ApplicationRegistered(uint256 index, string name, address owner, string url, address appAddr, uint256 status);

    function register(uint256 index, string name, address owner, string url, address appAddr, uint256 status) public;

}

contract ACashInOracle {

    event OpenCashAcceptor(uint256 sessionId, uint256 channelId, uint256 channelStatus, uint256 _maxAmount);
    event CloseCashAcceptor(uint256 sessionId, uint256 channelId);
    event OpenCashIn(uint256 _commandId, uint256 _sessionId, uint256 _cashInId, uint256 _maxBalance);

    function onNextOpenCashIn(uint256 _commandId) public returns (bool _accepted);

    function successOpen(uint256 _commandId) public;

    function failOpen(uint256 _commandId) public;

    function onNextCloseCashIn(uint256 _commandId) public returns (bool _accepted);

    function successClose(uint256 _commandId);

    function failClose(uint256 _commandId);

    function close(uint256 sessionId, uint256 channelId) public;

    function confirmClose(uint256 channelId) public;

    function increaseBalance(uint256 channelId, uint256 amount) public;

}

contract ASessionOracle {

    event CloseSession(uint256 sessionId);

    function closeSession(uint256 sessionId) public;

}

contract ACameraOracle {

    event StartScanQR(uint256 _commandId, uint256 _sessionId, bool _lights);
    event StopScanQR(uint256 _commandId, uint256 _sessionId);

    function onNextStartQRScan(uint256 _commandId) public returns (bool _accepted);

    function successStart(uint256 _commandId, string memory _port, string memory _url, string memory _href) public;

    function failStart(uint256 _commandId) public;

    function scanned(uint256 _sessionId, string memory _qr) public;

    function onNextStopQRScan(uint256 _commandId) public returns (bool _accepted);

    function successStop(uint256 _commandId) public;

    function failStop(uint256 _commandId) public;

}

contract APrinterOracle {

    event ReceiptCreate(uint256 _commandId, uint256 _sessionId);
    event ReceiptPrint(uint256 _commandId, uint256 _sessionId, string _receiptId, string _data, string _params);

    function onNextReceiptCreate(uint256 _commandId) public returns (bool _accepted);

    function successCreate(uint256 _commandId, string memory _receiptId, string memory _url) public;

    function failCreate(uint256 _commandId) public;

    function onNextReceiptPrint(uint256 _commandId) public returns (bool _accepted);

    function successPrint(uint256 _commandId) public;

    function failPrint(uint256 _commandId) public;

}
