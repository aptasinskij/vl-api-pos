pragma solidity 0.4.24;

import "../Platform.sol";
import "../controllers/Controllers.sol";

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

    function openCashInChannel(uint256 _sessionId, uint256 _maxAmount) external {
        bool accepted = ACashInController(context.get(CASH_IN_CONTROLLER)).openCashInChannel(
            _sessionId,
            _maxAmount,
            this.successOpen,
            this.balanceUpdate,
            this.failOpen
        );
        if (accepted) {
            lastOpenAcceptedSession = _sessionId;
            emit OpenAccepted(_sessionId);
        }
    }

    function successOpen(uint256 _sessionId, uint256 _cashInId) public {
        lastSuccessOpenSession = _sessionId;
        lastSuccessOpenChannel = _cashInId;
        emit SuccessOpen(_sessionId, _cashInId);
    }

    function balanceUpdate(uint256 _sessionId, uint256 _cashInId, uint256 _amount) public {
        lastBalanceUpdateSession = _sessionId;
        lastBalanceUpdateChannel = _cashInId;
        lastBalanceUpdateAmount = _amount;
        emit BalanceUpdate(_sessionId, _cashInId, _amount);
    }

    function failOpen(uint256 _sessionId) public {
        emit FailOpen(_sessionId);
    }

    function closeCashInChannel(uint256 _sessionId, uint256 _cashInId) public {
        uint256[] memory fees = new uint256[](0);
        address[] memory parties = new address[](0);
        bool accepted = ACashInController(context.get(CASH_IN_CONTROLLER)).closeCashInChannel(
            _sessionId,
            _cashInId,
            fees,
            parties,
            this.successClose,
            this.failClose
        );
        if (accepted) emit CloseAccepted(_sessionId);
    }

    function successClose(uint256 _sessionId, uint256 _cashInId) public {
        emit SuccessClose(_sessionId, _cashInId);
    }

    function failClose(uint256 _sessionId, uint256 _cashInId) public {
        emit FailClose(_sessionId, _cashInId);
    }

}