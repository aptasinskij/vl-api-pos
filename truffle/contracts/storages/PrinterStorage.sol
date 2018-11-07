pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "./api/APrinterStorage.sol";
import "../platform/Component.sol";
import "./libs/PrinterLib.sol";


contract PrinterStorage is APrinterStorage, Named("printer-storage"), Mortal, Component {

    PrinterLib.ReceiptCreate[] private create;
    PrinterLib.ReceiptPrint[] private print;

    constructor(address _config) Component(_config) public {}

    // @formatter:off
    function createReceiptCreate(
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success,
        function(uint256) external _fail
    )
        public
        returns (
            uint256 _id
        )
    // @formatter:on
    {
        _id = create.push(PrinterLib.ReceiptCreate(_sessionId, _success, _fail)) - 1;
    }

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
        )
    // @formatter:on
    {
        _sessionId = create[_id].sessionId;
        _success = create[_id].success;
        _fail = create[_id].fail;
    }

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
        )
    // @formatter:on
    {
        _id = print.push(PrinterLib.ReceiptPrint(_sessionId, _receiptId, _data, _paramNames, _paramValues, _success, _fail)) - 1;
    }

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
        )
    // @formatter:on
    {
        _sessionId = print[_id].sessionId;
        _receiptId = print[_id].receiptId;
        _data = print[_id].data;
        _paramNames = print[_id].paramNames;
        _paramValues = print[_id].paramValues;
        _success = print[_id].success;
        _fail = print[_id].fail;
    }

}