pragma solidity 0.4.24;

import {ASessionController} from "./Controllers.sol";
import {KioskLib, SessionLib} from "../libs/Libraries.sol";

contract SessionController is ASessionController {

    using SessionLib for address;

    string constant COMPONENT_NAME = "session-controller";

    constructor(address registry) ASessionController(registry) public {}

    function getName() internal pure returns (string name) {
        return COMPONENT_NAME;
    }

    function getKiosk(uint256 _sessionId) public onlyRegisteredApp view returns (string memory _id, string memory _location, string memory _name, string memory _timezone) {
        KioskLib.Kiosk memory kiosk = lookup("database").retrieveSessionKiosk(_sessionId);
        return (kiosk.id, kiosk.location, kiosk.name, kiosk.timezone);
    }

    function scanQRCodeWithLights(uint256 _sessionId) public view onlyRegisteredApp returns (bool _success, string memory _url) {
        _success = true;
        _url = "http://vaultlogic.com/preview-with-light-url-123asd";
    }

    function scanQRCode(uint256 _sessionId) public view onlyRegisteredApp returns (bool _success, string memory _url) {
        _success = true;
        _url = "http://vaultlogic.com/preview-url-123asd";
    }

    function stopQRScanning(uint256 _sessionId) public view onlyRegisteredApp returns (bool _success) {
        _success = true;
    }

    function getReceiptUrl(uint256 _sessionId) public view onlyRegisteredApp returns (bool _success, string memory _id, string memory _url) {
        _success = true;
        _id = "qwerty";
        _url = "http://vaultlogic.com/receipt-12345";
    }

    function printReceipt(uint256 _sessionId, string _id, string _data) public view onlyRegisteredApp returns (bool _success) {
        _success = true;
    }

}
