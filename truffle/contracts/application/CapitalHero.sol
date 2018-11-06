pragma solidity 0.4.24;

import "../platform/Context.sol";
import "../controllers/api/public/CashInApi.sol";
import "../controllers/api/public/CameraApi.sol";

contract CapitalHero {

    string constant CONTROLLER = "cash-in-controller";

    Context private context;

    constructor(address _context) public {
        context = Context(_context);
    }

    event RequestSended(uint256 _sessionId, uint256 _maxBalance);
    event CashInOpened(uint256 _sessionId, uint256 _cashInId);
    event CashInUpdate(uint256 _sessionId, uint256 _cashInId, uint256 _amount);
    event CashInFail(uint256 _sessionId);

    function open(uint256 _sessionId, uint256 _maxBalance) public {
        CashInApi(context.get(CONTROLLER)).openCashInChannel(
            _sessionId,
            _maxBalance,
            this._openFail,
            this._openSuccess,
            this._balanceUpdate
        );
        emit RequestSended(_sessionId, _maxBalance);
    }

    function _openFail(uint256 _sessionId) public {
        emit CashInFail(_sessionId);
    }

    function _openSuccess(uint256 _sessionId, uint256 _cashInId) public {
        emit CashInOpened(_sessionId, _cashInId);
    }

    function _balanceUpdate(uint256 _sessionId, uint256 _cashInId, uint256 _amount) public {
        emit CashInUpdate(_sessionId, _cashInId, _amount);
    }

    event ScanAccepted(uint256 _sessionId);
    event SuccessQR(uint256 _sessionId, string _port, string _url, string _href);
    event QRScanned(uint256 _sessionId, string _data);
    event QRFail(uint256 _sessionId);

    function scanQRWithLights(uint256 _sessionId) public {
        CameraApi(context.get("camera-controller")).scanQRCodeWithLights(
            _sessionId,
            this.__successStartScan,
            this.__QRScanned,
            this.__QRFail
        );
        emit ScanAccepted(_sessionId);
    }

    function __successStartScan(uint256 _sessionId, string _port, string _url, string _href) public {
        emit SuccessQR(_sessionId, _port, _url, _href);
    }

    function __QRScanned(uint256 _sessionId, string _data) public {
        emit QRScanned(_sessionId, _data);
    }

    function __QRFail(uint256 _sessionId) public {
        emit QRFail(_sessionId);
    }

}