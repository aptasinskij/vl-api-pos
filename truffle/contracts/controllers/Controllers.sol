pragma solidity 0.4.24;

contract ACashInController {

    function openCashInChannel(
        uint256 _sessionId,
        uint256 _maxBalance,
        function(uint256, uint256) external _success,
        function(uint256, uint256, uint256) external _update,
        function(uint256) external _fail
    )
        public
        returns (bool _accepted);

    function respondOpened(uint256 _sessionId, uint256 _cashInId, function(uint256, uint256) external _callback) public;

    function respondFailOpen(uint256 _sessionId, function(uint256) external _callback) public;

    function respondUpdate(
        uint256 _sessionId,
        uint256 _cashInId,
        uint256 _amount,
        function(uint256, uint256, uint256) external _callback
    ) public;

    function closeCashInChannel(
        uint256 _sessionId,
        uint256 _channelId,
        uint256[] _fees,
        address[] _parties,
        function(uint256, uint256) external _success,
        function(uint256, uint256) external _fail
    )
        public
        returns (bool _accepted);

    function respondClosed(uint256 _sessionId, uint256 _cashInId, function(uint256, uint256) external _callback) public;

    function respondFailClose(uint256 _sessionId, uint256 _cashInId, function(uint256, uint256) external _callback) public;

    function balanceOf(uint256 _channelId) public view returns (uint256);

}

contract ASessionController {

    function getKiosk(uint256 _sessionId) public view returns (string memory _id, string memory _location, string memory _name, string memory _timezone);

}

contract APrinterController {

    function createReceipt(
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success,
        function(uint256) external _fail
    )
        public
        returns (bool _accepted);

    function respondCreate(
        uint256 _sessionId,
        string memory _receiptId,
        string memory _url,
        function(uint256, string memory, string memory) external _callback
    ) public;

    function respondFailCreate(uint256 _sessionId, function(uint256) external _callback) public;

    function printReceipt(
        uint256 _sessionId,
        string memory _receiptId,
        string memory _data,
        string memory _params,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        public
        returns (bool _accepted);

    function respondPrint(uint256 _sessionId, function(uint256) external _callback) public;

    function respondFailPrint(uint256 _sessionId, function(uint256) external _callback) public;

}

contract ACameraController {

    ///@dev function to trigger QR code scanning with lights
    ///@param _sessionId - id of the session in which QR must occur
    ///@param _success - callback function for informing about successful case of enabling camera
    ///@param _scanned - callback function for informing about actual QR scanned data
    ///@param _fail - callback function for informing about failed case of enabling camera
    function scanQRCodeWithLights(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )
        public
        returns (bool _accepted);

    ///@dev function to trigger QR code scanning without lights
    ///@param _sessionId - id of the session in which QR must occur
    ///@param _success - callback function for informing about successful case of enabling camera
    ///@param _scanned - callback function for informing about actual QR scanned data
    ///@param _fail - callback function for informing about failed case of enabling camera
    function scanQRCode(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )
        public
        returns (bool _accepted);

    ///@dev function to trigger actual fail start QR scanning callback
    ///@param _sessionId - id of the session where fail occurred
    ///@param _callback - the callback function that would be triggered
    function respondFailStart(uint256 _sessionId, function(uint256) external _callback) public;

    ///@dev function to trigger actual success start QR scanning callback
    ///@param _sessionId - id of the session in which camera was successfully enabled
    ///@param _port - port of the camera preview
    ///@param _url - host url of the camera preview
    ///@param _href - link for pasting into front-end
    ///@param _callback - the callback function that would be triggered
    function respondStart(
        uint256 _sessionId,
        string memory _port,
        string memory _url,
        string memory _href,
        function(uint, string memory, string memory, string memory) external _callback
    )
        public;

    ///@dev function to trigger QR scanned callback
    ///@param _sessionId - id of the session in which QR was scanned
    ///@param _callback - the callback function that would be called
    function respondScanned(uint256 _sessionId, string memory _qr, function(uint256, string memory) external _callback) public;

    ///@dev function to disable QR scanning
    ///@param _sessionId - id of the session in which camera must be disabled
    ///@param _success - callback function to inform about camera successful disabling
    ///@param _fail - callback function to inform about camera disabling fail
    function stopQRScanning(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        public
        returns (bool _accepted);

    ///@dev function to trigger actual success of disabling camera
    ///@param _sessionId - the session id
    ///@param _callback - function that would be called
    function respondStop(uint256 _sessionId, function(uint256) external _callback) public;

    ///@dev function to trigger actual success of camera disabling fail
    ///@param _sessionId - the session id
    ///@param _callback - function that would be called
    function respondFailStop(uint256 _sessionId, function(uint256) external _callback) public;

}