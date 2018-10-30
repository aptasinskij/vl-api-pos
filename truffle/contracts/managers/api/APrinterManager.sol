pragma solidity 0.4.24;

contract APrinterManager {

    // @formatter:off
    function createReceipt(
        address _application,
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success,
        function(uint256) external _fail
    )
        public
        returns (
            bool _accepted
        );
    // @formatter:on

    function confirmCreate(uint256 _commandId, string memory _receiptId, string memory _url) public;

    function confirmFailCreate(uint256 _commandId) public;

    // @formatter:off
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
        returns (
            bool _accepted
        );
    // @formatter:on

    function confirmPrint(uint256 _commandId) public;

    function confirmFailPrint(uint256 _commandId) public;

}
