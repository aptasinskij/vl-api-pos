pragma solidity 0.4.24;

interface PrinterApi {

    // @formatter:off
    function createReceipt(
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success,
        function(uint256) external _fail
    )
        public;
    // @formatter:on

    // @formatter:off
    function printReceipt(
         uint256 _sessionId,
         string _receiptId,
         string _data,
         bytes32[] _paramNames,
         bytes32[] _paramValues,
         function(uint256) external _success,
         function(uint256) external _fail
     )
         public;
    // @formatter:on

}
