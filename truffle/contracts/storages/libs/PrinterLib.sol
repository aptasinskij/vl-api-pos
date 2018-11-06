pragma solidity 0.4.24;

library PrinterLib {

    struct ReceiptCreate {
        uint256 sessionId;
        function(uint256, string memory, string memory) external success;
        function(uint256) external fail;
    }

    struct ReceiptPrint {
        uint256 sessionId;
        string receiptId;
        string data;
        string params;
        function(uint256) external success;
        function(uint256) external fail;
    }

}
