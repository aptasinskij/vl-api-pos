pragma solidity 0.4.24;

import "../platform/Context.sol";
import "../controllers/api/public/CashOutApi.sol";

contract CapitalHero {

    string constant CONTROLLER = "cash-in-controller";

    Context private context;

    constructor(address _context) public {
        context = Context(_context);
    }

    /*
        Example of CashOutApi workflow
    */

    string constant CASH_OUT_API = "cash-out-controller";

    // Open cash out components:

    event OpenCashOutAccepted();
    event OpenCashOutSuccess(string _requestId, string _kioskId, uint256 _cashOutId, uint256 _fee);
    event OpenCashOutFailed(string _kioskId, string _requestId);

    function openCashOut(
        string _requestId,
        string _kioskId,
        uint256 _toWithdraw,
        uint256[] _fees,
        address[] _parties
    )
    public
    {
        CashOutApi(context.get(CASH_OUT_API)).openCashOutChannel(
            _requestId,
            _kioskId,
            _toWithdraw,
            _fees,
            _parties,
            this.__failOpenCashOut,
            this.__successOpenCashOut
        );
        emit OpenCashOutAccepted();
    }

    function __successOpenCashOut(string _requestId, string _kioskId, uint256 _cashOutId, uint256 _fee) public {
        emit OpenCashOutSuccess(_requestId, _kioskId, _cashOutId, _fee);
    }

    function __failOpenCashOut( string _requestId, string _kioskId) public {
        emit OpenCashOutFailed(_requestId, _kioskId);
    }

    // Validate cash out components:

    event ValidateCashOutAccepted();
    event ValidateCashOutSuccess(uint256 _sessionId, uint256 _cashOutId);
    event ValidateCashOutFailed(uint256 _sessionId, uint256 _cashOutId);

    function validateCashOut(uint256 _sessionId, uint256 _cashOutId) public {
        CashOutApi(context.get(CASH_OUT_API)).validateCashOutChannel(
            _sessionId,
            _cashOutId,
            this.__failValidateCashOut,
            this.__successValidateCashOut
        );
        emit ValidateCashOutAccepted();
    }

    function __successValidateCashOut(uint256 _sessionId, uint256 _cashOutId) public {
        emit ValidateCashOutSuccess(_sessionId, _cashOutId);
    }

    function __failValidateCashOut(uint256 _sessionId, uint256 _cashOutId) public {
        emit ValidateCashOutFailed(_sessionId, _cashOutId);
    }

    // Close cash out components:

    event CloseCashOutAccepted();
    event CloseCashOutSuccess(uint256 _sessionId, uint256 _cashOutId);
    event CloseCashOutFailed(uint256 _sessionId, uint256 _cashOutId);

    function closeCashOut(uint256 _sessionId, uint256 _cashOutId) public {
        CashOutApi(context.get(CASH_OUT_API)).closeCashOutChannel(
            _sessionId,
            _cashOutId,
            this.__failCloseCashOut,
            this.__successCloseCashOut
        );
        emit CloseCashOutAccepted();
    }

    function __successCloseCashOut(uint256 _sessionId, uint256 _cashOutId) public {
        emit CloseCashOutSuccess(_sessionId, _cashOutId);
    }

    function __failCloseCashOut(uint256 _sessionId, uint256 _cashOutId) public {
        emit CloseCashOutFailed(_sessionId, _cashOutId);
    }

    // Rollback cash out components:

    event RollbackCashOutAccepted();
    event RollbackCashOutSuccess(uint256 _cashOutId);
    event RollbackCashOutFailed(uint256 _cashOutId);

    function rollbackCashOut(uint256 _cashOutId) public {
        CashOutApi(context.get(CASH_OUT_API)).rollbackCashOutChannel(
            _cashOutId,
            this.__failRollbackCashOut,
            this.__successRollbackCashOut
        );
        emit RollbackCashOutAccepted();
    }

    function __successRollbackCashOut(uint256 _cashOutId) public {
        emit RollbackCashOutSuccess(_cashOutId);
    }

    function __failRollbackCashOut(uint256 _cashOutId) public {
        emit RollbackCashOutFailed(_cashOutId);
    }

}