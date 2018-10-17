pragma solidity 0.4.24;

import "./IApplication.sol";
import "./RegistryDependent.sol";
import "../managers/Managers.sol";
import {ASessionController} from "../controllers/Controllers.sol";
import {ACashInController} from "../controllers/Controllers.sol";

contract CapitalHero is IApplication, RegistryDependent {

    string constant CASH_IN_CONTROLLER = "cash-in-controller";
    string constant SESSION_MANAGER = "session-manager";
    string constant SESSION_CONTROLLER = "session-controller";

    event CashInOpened(uint256 channelId, uint256 sessionId);
    event CashInClosed(uint256 channelId, uint256 sessionId);
    event CashInBalanceUpdated(uint256 channelId, uint256 balance, uint256 sessionId);
    event SessionCreated(uint256 timestamp, uint256 sessionId);
    event SessionClosed(uint256 timestamp, uint256 sessionId);

    constructor(address regAddr) RegistryDependent(regAddr) public {}

    function openCashInChannel(uint256 sessionId, uint256 _maxAmount) external {
        ACashInController(componentForName(CASH_IN_CONTROLLER)).open(sessionId, _maxAmount);
    }

    function closeCashInChannel(uint256 sessionId, uint256 channelId, uint256[] _fees, address[] _parties) external {
        ACashInController(componentForName(CASH_IN_CONTROLLER)).close(sessionId, channelId, _fees, _parties);
    }

    function cashInChannelOpened(uint256 channelId, uint256 sessionId) external {
        emit CashInOpened(channelId, sessionId);
    }

    function cashInBalanceUpdate(uint256 channelId, uint256 balance, uint256 sessionId) external {
        emit CashInBalanceUpdated(channelId, balance, sessionId);
    }

    function cashInChannelClosed(uint256 channelId, uint256 sessionId) external {
        emit CashInClosed(channelId, sessionId);
    }

    function newSessionCreated(uint256 _sessionId) external {
        emit SessionCreated(now, _sessionId);
    }

    function closeSession(uint256 sessionId) external {
        ASessionManager(componentForName(SESSION_MANAGER)).closeSession(sessionId);
    }

    function sessionClosed(uint256 sessionId) external {
        emit SessionClosed(now, sessionId);
    }

    function getKioskInfo(uint256 _sessionId) public view returns (string memory _id, string memory _location, string memory _name, string memory _timezone) {
        return ASessionController(componentForName(SESSION_CONTROLLER)).getKiosk(_sessionId);
    }

    event QRCodeScanned(uint256 sessionId, string url);
    event QRScanningStopped(uint256 sessionId);
    event ReceiptURLReceived(uint256 sessionId, string id, string url);
    event ReceiptPrinted(uint256 sessionId, string id, string data);

    function scanQRCodeWithLights(uint256 _sessionId) public {
        (bool success, string memory url) = ASessionController(componentForName(SESSION_CONTROLLER)).scanQRCodeWithLights(_sessionId);
        if (success) emit QRCodeScanned(_sessionId, url);
    }

    function scanQRCode(uint256 _sessionId) public {
        (bool success, string memory url) = ASessionController(componentForName(SESSION_CONTROLLER)).scanQRCode(_sessionId);
        if (success) emit QRCodeScanned(_sessionId, url);
    }

    function stopQRScanning(uint256 _sessionId) public {
        bool success = ASessionController(componentForName(SESSION_CONTROLLER)).stopQRScanning(_sessionId);
        if (success) emit QRScanningStopped(_sessionId);
    }

    function getReceiptUrl(uint256 _sessionId) public {
        (bool success, string memory id, string memory url) = ASessionController(componentForName(SESSION_CONTROLLER)).getReceiptUrl(_sessionId);
        if (success) emit ReceiptURLReceived(_sessionId, id, url);
    }

    function printReceipt(uint256 _sessionId, string _id, string _data) public {
        bool success = ASessionController(componentForName(SESSION_CONTROLLER)).printReceipt(_sessionId, _id, _data);
        if (success) emit ReceiptPrinted(_sessionId, _id, _data);
    }

}