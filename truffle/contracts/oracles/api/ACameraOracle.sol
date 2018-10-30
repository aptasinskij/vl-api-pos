pragma solidity 0.4.24;

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
