pragma solidity 0.4.24;

import {PrinterLib} from "../libs/Libraries.sol";
import "../platform/Mortal.sol";
import "../platform/Named.sol";
import "../platform/Component.sol";
import "../managers/api/APrinterManager.sol";
import "./api/APrinterOracle.sol";

contract PrinterOracle is APrinterOracle, Mortal, Named("printer-oracle"), Component {

    using PrinterLib for address;

    string constant MANAGER = "printer-manager";

    constructor(address _config) Component(_config) public {}

    function onNextReceiptCreate(uint256 _commandId) public returns (bool _accepted) {
        PrinterLib.ReceiptCreate memory command = database.retrieveReceiptCreate(_commandId);
        emit ReceiptCreate(_commandId, command.sessionId);
        _accepted = true;
    }

    function successCreate(uint256 _commandId, string memory _receiptId, string memory _url) public onlyOwner {
        APrinterManager(context.get(MANAGER)).confirmCreate(_commandId, _receiptId, _url);
    }

    function failCreate(uint256 _commandId) public onlyOwner {
        APrinterManager(context.get(MANAGER)).confirmFailCreate(_commandId);
    }

    function onNextReceiptPrint(uint256 _commandId) public returns (bool _accepted) {
        PrinterLib.ReceiptPrint memory command = database.retrieveReceiptPrint(_commandId);
        emit ReceiptPrint(_commandId, command.sessionId, command.receiptId, command.data, command.params);
        _accepted = true;
    }

    function successPrint(uint256 _commandId) public onlyOwner {
        APrinterManager(context.get(MANAGER)).confirmPrint(_commandId);
    }

    function failPrint(uint256 _commandId) public onlyOwner {
        APrinterManager(context.get(MANAGER)).confirmFailPrint(_commandId);
    }

}
