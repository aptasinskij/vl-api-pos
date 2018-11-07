pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/APrinterController.sol";
import "../managers/api/APrinterManager.sol";

contract PrinterController is APrinterController, Named("printer-controller"), Mortal, Component {

    string constant MANAGER = "printer-manager";

    constructor(address _config) Component(_config) public {}

    function createReceipt(
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success,
        function(uint256) external _fail
    )
        public
    {
        APrinterManager(context.get(MANAGER)).createReceipt(msg.sender, _sessionId, _success, _fail);
    }

    function respondCreate(
        uint256 _sessionId,
        string _receiptId,
        string _url,
        function(uint256, string memory, string memory) external _callback
    )
        public
    {
        _callback(_sessionId, _receiptId, _url);
    }

    function respondFailCreate(uint256 _sessionId, function(uint256) external _callback) public {
        _callback(_sessionId);
    }

    function printReceipt(
        uint256 _sessionId,
        string _receiptId,
        string _data,
        bytes32[] _paramNames,
        bytes32[] _paramValues,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        public
    {
        APrinterManager(context.get(MANAGER)).printReceipt(msg.sender, _sessionId, _receiptId, _data, _paramNames, _paramValues, _success, _fail);
    }

    function respondPrint(uint256 _sessionId, function(uint256) external _callback) public {
        _callback(_sessionId);
    }

    function respondFailPrint(uint256 _sessionId, function(uint256) external _callback) public {
        _callback(_sessionId);
    }

}
