pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/APrinterManager.sol";
import "../oracles/api/APrinterOracle.sol";
import "../controllers/api/APrinterController.sol";
import {PrinterLib} from "../libs/Libraries.sol";

contract PrinterManager is APrinterManager, Mortal, Named("printer-manager"), Component {

    using PrinterLib for address;

    string constant ORACLE = "printer-oracle";
    string constant CONTROLLER = "printer-controller";

    constructor(address _config) Component(_config) public {}

    function createReceipt(
        address _application,
        uint256 _sessionId,
        function(uint256, string memory, string memory) external _success,
        function(uint256) external _fail
    )
        public
        returns (bool _accepted)
    {
        /*address sessionOwner = database.retrieveSessionApplicationDeployedAddress(_sessionId);
        require(sessionOwner == _application, "illegal session access");
        require(database.sessionIsActive(_sessionId), "session is not active");*/
        uint256 receiptCreateId = database.getNextReceiptCreateId();
        // @formatter:off
        database.createReceiptCreate(PrinterLib.ReceiptCreate({
            id: receiptCreateId,
            sessionId: _sessionId,
            success: _success,
            fail: _fail
        }));
        // @formatter:on
        return APrinterOracle(context.get(ORACLE)).onNextReceiptCreate(receiptCreateId);
    }

    function confirmCreate(uint256 _commandId, string memory _receiptId, string memory _url) public {
        PrinterLib.ReceiptCreate memory command = database.retrieveReceiptCreate(_commandId);
        APrinterController(context.get(CONTROLLER)).respondCreate(command.sessionId, _receiptId, _url, command.success);
    }

    function confirmFailCreate(uint256 _commandId) public {
        PrinterLib.ReceiptCreate memory command = database.retrieveReceiptCreate(_commandId);
        APrinterController(context.get(CONTROLLER)).respondFailCreate(command.sessionId, command.fail);
    }

    function printReceipt(
        address _application,
        uint256 _sessionId,
        string memory _receiptId,
        string memory _data,
        string memory _params,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        public
        returns (bool _accepted)
    {
        /*address sessionOwner = database.retrieveSessionApplicationDeployedAddress(_sessionId);
        require(sessionOwner == _application, "illegal session access");
        require(database.sessionIsActive(_sessionId), "session is not active");*/
        uint256 receiptPrintId = database.getNextReceiptPrintId();
        // @formatter:off
        database.createReceiptPrint(PrinterLib.ReceiptPrint({
            id: receiptPrintId,
            sessionId: _sessionId,
            receiptId: _receiptId,
            data: _data,
            params: _params,
            success: _success,
            fail: _fail
        }));
        // @formatter:on
        return APrinterOracle(context.get(ORACLE)).onNextReceiptPrint(receiptPrintId);
    }

    function confirmPrint(uint256 _commandId) public {
        PrinterLib.ReceiptPrint memory command = database.retrieveReceiptPrint(_commandId);
        APrinterController(context.get(CONTROLLER)).respondPrint(command.sessionId, command.success);
    }

    function confirmFailPrint(uint256 _commandId) public {
        PrinterLib.ReceiptPrint memory command = database.retrieveReceiptPrint(_commandId);
        APrinterController(context.get(CONTROLLER)).respondFailPrint(command.sessionId, command.fail);
    }

}
