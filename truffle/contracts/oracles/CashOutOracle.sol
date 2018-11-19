pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/ACashOutOracle.sol";
import "../managers/api/ACashOutManager.sol";

contract CashOutOracle is ACashOutOracle, Named("cash-out-oracle"), Mortal, Component {

    string constant MANAGER = "cash-out-manager";

    constructor(address _config) Component(_config) public {}

    function onNextOpenCashOut(uint256 _commandId) public {
        emit OpenCashOut(_commandId);
    }

    function successOpen(uint256 _commandId) public {
        ACashOutManager(context.get(MANAGER)).confirmOpen(_commandId);
    }

    function failOpen(uint256 _commandId) public {
        ACashOutManager(context.get(MANAGER)).confirmFailOpen(_commandId);
    }

    function onNextValidateCashOut(uint256 _commandId, uint256 _sessionId, uint256 _toWithdraw, uint256[] _bills) public {
        emit ValidateCashOut(_commandId, _sessionId, _toWithdraw, _bills);
    }

    function successValidate(uint256 _commandId) public {
        ACashOutManager(context.get(MANAGER)).confirmValidate(_commandId);
    }

    function failValidate(uint256 _commandId) public {
        ACashOutManager(context.get(MANAGER)).confirmFailValidate(_commandId);
    }

    function onNextCloseCashOut(uint256 _commandId, uint256 _sessionId, uint256 _toWithdraw, uint256[] _bills) public {
        emit CloseCashOut(_commandId, _sessionId, _toWithdraw, _bills);
    }

    function successClose(uint256 _commandId) public {
        ACashOutManager(context.get(MANAGER)).confirmClose(_commandId);
    }

    function failClose(uint256 _commandId) public {
        ACashOutManager(context.get(MANAGER)).confirmFailClose(_commandId);
    }

    function onNextRollbackCashOut(uint256 _commandId) public {
        emit RollbackCashOut(_commandId);
    }

    function successRollback(uint256 _commandId) public {
        ACashOutManager(context.get(MANAGER)).confirmRollback(_commandId);
    }

    function failRollback(uint256 _commandId) public {
        ACashOutManager(context.get(MANAGER)).confirmFailRollback(_commandId);
    }

}