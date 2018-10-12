pragma solidity 0.4.24;

contract ACameraOracle {

    event StartScanQR(uint256 _commandId, uint256 _sessionId, bool _lights);
    event StopScanQR(uint256 _commandId, uint256 _sessionId);

    function onNextStartQRScan(uint256 _commandId) public returns (bool _accepted);

    function successStart(uint256 _commandId) public;

    function qrScanned(uint256 _sessionId, string memory _port, string memory _url, string memory _href) public;

    function failQRScan(uint256 _commandId) public;

    function onNextStopQRScan(uint256 _commandId) public returns (bool _accepted);

    function successStopQRScan(uint256 _commandId) public;

    function failStopQRScan(uint256 _commandId) public;

}
