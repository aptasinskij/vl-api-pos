pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/APrinterController.sol";
import {ApplicationLib} from "../libs/Libraries.sol";
import "../managers/api/APrinterManager.sol";

contract PrinterController is APrinterController, Named("printer-controller"), Mortal, Component {

    using ApplicationLib for address;

    string constant MANAGER = "printer-manager";

    modifier isRegistered {
        require(database.isRegistered(msg.sender), "only registered allowed");
        _;
    }

    modifier onlyManager {
        require(msg.sender == context.get(MANAGER), "only manager allowed");
        _;
    }

    constructor(address _config) Component(_config) public {}

    function createReceipt(
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success,
        function(uint256) external _fail
    )
        external
        isRegistered
        returns (bool _accepted)
    {
        return APrinterManager(context.get(MANAGER)).createReceipt(msg.sender, _sessionId, _success, _fail);
    }

    function respondCreate(
        uint256 _sessionId,
        string memory _receiptId,
        string memory _url,
        function(uint256, string memory, string memory) external _callback
    )
        public
        onlyManager
    {
        _callback(_sessionId, _receiptId, _url);
    }

    function respondFailCreate(uint256 _sessionId, function(uint256) external _callback) public onlyManager {
        _callback(_sessionId);
    }

    function printReceipt(
        uint256 _sessionId,
        string _receiptId,
        string _data,
        string _params,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        external
        isRegistered
        returns (bool _accepted)
    {
        return APrinterManager(context.get(MANAGER)).printReceipt(msg.sender, _sessionId, _receiptId, _data, _params, _success, _fail);
    }

    function respondPrint(uint256 _sessionId, function(uint256) external _callback) public onlyManager {
        _callback(_sessionId);
    }

    function respondFailPrint(uint256 _sessionId, function(uint256) external _callback) public onlyManager {
        _callback(_sessionId);
    }

}
