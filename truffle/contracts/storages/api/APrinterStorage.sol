pragma solidity 0.4.24;

contract APrinterStorage {

    // @formatter:off
    function createReceiptCreate(
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success,
        function(uint256) external _fail
    )
        public
        returns (
            uint256 _id
        );
    // @formatter:on

    // @formatter:off
    function getReceiptCreate(
        uint256 _id
    )
        public
        view
        returns (
            uint256 _sessionId,
            function(uint256, string memory, string memory) external _success,
            function(uint256) external _fail
        );
    // @formatter:on

    // @formatter:off
    function createReceiptPrint(
        uint256 _sessionId,
        string _receiptId,
        string _data,
        bytes32[] _paramNames,
        bytes32[] _paramValues,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        public
        returns (
            uint256 _id
        );
    // @formatter:on

    // @formatter:off
    function getReceiptPrint(
        uint256 _id
    )
        public
        view
        returns (
            uint256 _sessionId,
            string _receiptId,
            string _data,
            bytes32[] _paramNames,
            bytes32[] _paramValues,
            function(uint256) external _success,
            function(uint256) external _fail
        );
    // @formatter:on
}
