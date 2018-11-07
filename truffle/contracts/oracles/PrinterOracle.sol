pragma solidity 0.4.24;

import "../platform/Mortal.sol";
import "../platform/Named.sol";
import "../platform/Component.sol";
import "../managers/api/APrinterManager.sol";
import "./api/APrinterOracle.sol";

contract PrinterOracle is APrinterOracle, Mortal, Named("printer-oracle"), Component {

    string constant MANAGER = "printer-manager";

    constructor(address _config) Component(_config) public {}

    function onNextReceiptCreate(uint256 _commandId, uint256 _sessionId) public {
        emit ReceiptCreate(_commandId, _sessionId);
    }

    function successCreate(uint256 _commandId, string _receiptId, string _url) public onlyOwner {
        APrinterManager(context.get(MANAGER)).confirmCreate(_commandId, _receiptId, _url);
    }

    function failCreate(uint256 _commandId) public onlyOwner {
        APrinterManager(context.get(MANAGER)).confirmFailCreate(_commandId);
    }

    function onNextReceiptPrint(uint256 _commandId, uint256 _sessionId, string _receiptId, string _data, bytes32[] _paramNames, bytes32[] _paramValues) public {
        emit ReceiptPrint(_commandId, _sessionId, _receiptId, _data, _paramNames, _paramValues);
    }

    function successPrint(uint256 _commandId) public onlyOwner {
        APrinterManager(context.get(MANAGER)).confirmPrint(_commandId);
    }

    function failPrint(uint256 _commandId) public onlyOwner {
        APrinterManager(context.get(MANAGER)).confirmFailPrint(_commandId);
    }

}
