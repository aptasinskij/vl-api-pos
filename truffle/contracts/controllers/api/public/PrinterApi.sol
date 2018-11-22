pragma solidity 0.4.24;

contract PrinterApi {

    /**
    @notice Create Receipt
    @dev
    @param _sessionId id of the session in which receipt must create
    @param _success callback function(_commandId, _receiptId, _url) for informing about successful case of creating receipt
    @param _fail callback function(_commandId) for informing about failed case of creating receipt
    @return {
    }
    */
    // @formatter:off
    function createReceipt(
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success,
        function(uint256) external _fail
    )
        public;
    // @formatter:on

    /**
    @notice Create CashIn channel
    @dev
    @param _sessionId id of the session in which receipt must print
    @param _receiptId id of the receipt which we need to print
    @param _data information on the receipt which kiosk needs to print
    @param _paramNames list of parameters names from application
    @param _paramValues list of parameters values from application
    @param _success callback function(_commandId) for informing about successful case of receipt printing
    @param _fail callback function(_commandId) for informing about failed case of receipt printing
    @return {
    }
    */
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
