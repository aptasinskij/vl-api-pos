pragma solidity 0.4.24;

import "../platform/Context.sol";
import "../controllers/api/ACameraController.sol";

contract CapitalHero {

    event OpenAccepted(uint256 _sessionId);
    event SuccessOpen(uint256 _sessionId, uint256 _cashInId);
    event BalanceUpdate(uint256 _sessionId, uint256 _cashInId, uint256 _amount);
    event FailOpen(uint256 _sessionId);

    event CloseAccepted(uint256 _sessionId);
    event SuccessClose(uint256 _sessionId, uint256 _cashInId);
    event FailClose(uint256 _sessionId, uint256 _cashInId);

    string constant CASH_IN_CONTROLLER = "cash-in-controller";

    Context private context;

    uint256 public lastOpenAcceptedSession;

    uint256 public lastSuccessOpenSession;
    uint256 public lastSuccessOpenChannel;

    uint256 public lastBalanceUpdateSession;
    uint256 public lastBalanceUpdateChannel;
    uint256 public lastBalanceUpdateAmount;

    constructor(address _context) public {
        context = Context(_context);
    }

    event ScanQRAccepted(uint256 _sessionId);
    event ScanQRSuccess(uint256 _sessionId, string _port, string _url, string _href);
    event QRScanned(uint256 _sessionId, string _data);
    event QRFail(uint256 _sessionId);

    function scanQRCode(uint256 _sessionId) public {
        bool accepted = ACameraController(context.get("camera-controller")).scanQRCodeWithLights(_sessionId, this.successScan, this.scanned, this.failScan);
        if (accepted) emit ScanQRAccepted(_sessionId);
    }

    function successScan(uint256 _sessionId, string memory _port, string memory _url, string memory _href) public {
        emit ScanQRSuccess(_sessionId, _port, _url, _href);
    }

    function scanned(uint256 _sessionId, string memory _data) public {
        emit QRScanned(_sessionId, _data);
    }

    function failScan(uint256 _sessionId) public {
        emit QRFail(_sessionId);
    }

    event StopAccepted(uint256 _sessionId);
    event SuccessStop(uint256 _sessionId);

    function stopQRScan(uint256 _sessionId) public {
        bool accepted = ACameraController(context.get("camera-controller")).stopQRScanning(_sessionId, this.successStop, this.successStop);
        if (accepted) emit StopAccepted(_sessionId);
    }

    function successStop(uint256 _sessionId) public {
        emit SuccessStop(_sessionId);
    }

}