pragma solidity 0.4.24;

import "../platform/Context.sol";
import "../controllers/api/ACameraController.sol";

contract CapitalHero {

    string constant CASH_IN_CONTROLLER = "cash-in-controller";

    Context private context;

    constructor(address _context) public {
        context = Context(_context);
    }

    event ScanQRAccepted(uint256 _sessionId);
    event ScanQRSuccess(uint256 _sessionId, string _port, string _url, string _href);
    event QRScanned(uint256 _sessionId, string _data);
    event QRFail(uint256 _sessionId);

    function scanQRCode(uint256 _sessionId) public {
        ACameraController(context.get("camera-controller")).scanQRCodeWithLights(_sessionId, this.successScan, this.scanned, this.failScan);
        emit ScanQRAccepted(_sessionId);
    }

    function successScan(uint256 _sessionId, string _port, string _url, string _href) public {
        emit ScanQRSuccess(_sessionId, _port, _url, _href);
    }

    function scanned(uint256 _sessionId, string _data) public {
        emit QRScanned(_sessionId, _data);
    }

    function failScan(uint256 _sessionId) public {
        emit QRFail(_sessionId);
    }

    event StopAccepted(uint256 _sessionId);
    event SuccessStop(uint256 _sessionId);

    function stopQRScan(uint256 _sessionId) public {
        ACameraController(context.get("camera-controller")).stopQRScanning(_sessionId, this.successStop, this.successStop);
        emit StopAccepted(_sessionId);
    }

    function successStop(uint256 _sessionId) public {
        emit SuccessStop(_sessionId);
    }

}