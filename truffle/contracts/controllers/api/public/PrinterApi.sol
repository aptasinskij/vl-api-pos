pragma solidity 0.4.24;

interface PrinterApi {

    // @formatter:off
    function createReceipt(
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success,
        function(uint256) external _fail
    )
        external
        returns (
            bool _accepted
        );
    // @formatter:on

    // @formatter:off
    function printReceipt(
        uint256 _sessionId,
        string memory _receiptId,
        string memory _data,
        string memory _params,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        external
        returns (
            bool _accepted
        );
    // @formatter:on

}
