pragma solidity 0.4.24;

import "./public/PrinterApi.sol";

contract APrinterController is PrinterApi {

    // @formatter:off
    function respondCreate(
        uint256 _sessionId,
        string memory _receiptId,
        string memory _url,
        function(uint256, string memory, string memory) external _callback
    )
        public;
    // @formatter:on

    // @formatter:off
    function respondFailCreate(
        uint256 _sessionId,
        function(uint256) external _callback
    )
        public;
    // @formatter:on

    // @formatter:off
    function respondPrint(
        uint256 _sessionId,
        function(uint256) external _callback
    )
        public;
    // @formatter:on

    // @formatter:off
    function respondFailPrint(
        uint256 _sessionId,
        function(uint256) external _callback
    )
        public;
    // @formatter:on

}
