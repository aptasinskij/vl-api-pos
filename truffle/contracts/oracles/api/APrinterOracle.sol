pragma solidity 0.4.24;

contract APrinterOracle {

    event ReceiptCreate(uint256 _commandId, uint256 _sessionId);
    event ReceiptPrint(uint256 _commandId, uint256 _sessionId, string _receiptId, string _data, string _params);

    function onNextReceiptCreate(uint256 _commandId) public returns (bool _accepted);

    function successCreate(uint256 _commandId, string memory _receiptId, string memory _url) public;

    function failCreate(uint256 _commandId) public;

    function onNextReceiptPrint(uint256 _commandId) public returns (bool _accepted);

    function successPrint(uint256 _commandId) public;

    function failPrint(uint256 _commandId) public;

}