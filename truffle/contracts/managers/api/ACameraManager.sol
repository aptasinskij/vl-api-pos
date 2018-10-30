pragma solidity 0.4.24;

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
