pragma solidity 0.4.24;

contract ACameraOracle {

    event StartScanQR(uint256 _commandId, uint256 _sessionId, bool _lights);
    event StopScanQR(uint256 _commandId, uint256 _sessionId);

    function onNextStartQRScan(uint256 _commandId, uint256 _sessionId, bool _lights) public;

    function successStart(uint256 _commandId, string _port, string _url, string _href) public;

    function failStart(uint256 _commandId) public;

    function scanned(uint256 _sessionId, string _qr) public;

    function onNextStopQRScan(uint256 _commandId, uint256 _sessionId) public;

    function successStop(uint256 _commandId) public;

    function failStop(uint256 _commandId) public;

}
