pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/APrinterManager.sol";
import "../oracles/api/APrinterOracle.sol";
import "../storages/api/APrinterStorage.sol";
import "../controllers/api/APrinterController.sol";

contract PrinterManager is APrinterManager, Mortal, Named("printer-manager"), Component {

    string constant STORAGE = "printer-storage";
    string constant ORACLE = "printer-oracle";
    string constant CONTROLLER = "printer-controller";

    constructor(address _config) Component(_config) public {}

    // @formatter:off
    function createReceipt(
        address _application,
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success,
        function(uint256) external _fail
    )
        public
    // @formatter:on
    {
        uint256 _id = APrinterStorage(context.get(STORAGE)).createReceiptCreate(_sessionId, _success, _fail);
        APrinterOracle(context.get(ORACLE)).onNextReceiptCreate(_id, _sessionId);
    }

    function confirmCreate(uint256 _commandId, string _receiptId, string _url) public {
        (uint256 _sessionId,function(uint256, string memory, string memory) external callback,) = APrinterStorage(context.get(STORAGE)).
        getReceiptCreate(_commandId);
        APrinterController(context.get(CONTROLLER)).respondCreate(_sessionId, _receiptId, _url, callback);
    }

    function confirmFailCreate(uint256 _commandId) public {
        (uint256 _sessionId,,function(uint256) external callback) = APrinterStorage(context.get(STORAGE)).getReceiptCreate(_commandId);
        APrinterController(context.get(CONTROLLER)).respondFailCreate(_sessionId, callback);
    }

    // @formatter:off
    function printReceipt(
        address _application,
        uint256 _sessionId,
        string _receiptId,
        string _data,
        bytes32[] _paramNames,
        bytes32[] _paramValues,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        public
    // @formatter:on
    {
        uint256 _id = APrinterStorage(context.get(STORAGE)).createReceiptPrint(_sessionId, _receiptId, _data, _paramNames, _paramValues, _success, _fail);
        APrinterOracle(context.get(ORACLE)).onNextReceiptPrint(_id, _sessionId, _receiptId, _data, _paramNames, _paramValues);
    }

    function confirmPrint(uint256 _commandId) public {
        (uint256 _sessionId,,,,,function(uint256) external callback,) = APrinterStorage(context.get(STORAGE)).getReceiptPrint(_commandId);
        APrinterController(context.get(CONTROLLER)).respondPrint(_sessionId, callback);
    }

    function confirmFailPrint(uint256 _commandId) public {
        (uint256 _sessionId,,,,,,function(uint256) external callback) = APrinterStorage(context.get(STORAGE)).getReceiptPrint(_commandId);
        APrinterController(context.get(CONTROLLER)).respondPrint(_sessionId, callback);
    }

}
