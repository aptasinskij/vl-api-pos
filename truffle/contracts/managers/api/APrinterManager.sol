pragma solidity 0.4.24;

contract APrinterManager {

    function createReceipt(
        address _application,
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success,
        function(uint256) external _fail
    )
        public;

    function confirmCreate(uint256 _commandId, string _receiptId, string _url) public;

    function confirmFailCreate(uint256 _commandId) public;

    function printReceipt(
        address _application,
        uint256 _sessionId,
        string _receiptId,
        string _data,
        string _params,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        public;

    function confirmPrint(uint256 _commandId) public;

    function confirmFailPrint(uint256 _commandId) public;

}
