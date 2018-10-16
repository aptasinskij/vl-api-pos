pragma solidity 0.4.24;

contract AnApplicationOracle {

    event ApplicationRegistered(uint256 index, string name, address owner, string url, address appAddr, uint256 status);

    function register(uint256 index, string name, address owner, string url, address appAddr, uint256 status) public;

}

contract ACashInOracle {

    event OpenCashAcceptor(uint256 sessionId, uint256 channelId, uint256 channelStatus);
    event CloseCashAcceptor(uint256 sessionId, uint256 channelId);

    function open(uint256 sessionId, uint256 channelId, uint256 channelStatus) public;

    function close(uint256 sessionId, uint256 channelId) public;

    function confirmOpen(uint256 channelId) public;

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
