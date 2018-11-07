pragma solidity 0.4.24;

contract APrinterOracle {

    event ReceiptCreate(uint256 _commandId, uint256 _sessionId);
    event ReceiptPrint(uint256 _commandId, uint256 _sessionId, string _receiptId, string _data, bytes32[] _paramNames, bytes32[] _paramValues);

    function onNextReceiptCreate(uint256 _commandId, uint256 _sessionId) public;

    function successCreate(uint256 _commandId, string _receiptId, string _url) public;

    function failCreate(uint256 _commandId) public;

    function onNextReceiptPrint(uint256 _commandId, uint256 _sessionId, string _receiptId, string _data, bytes32[] _paramNames, bytes32[] _paramValues) public;

    function successPrint(uint256 _commandId) public;

    function failPrint(uint256 _commandId) public;

}