pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/ACashOutOracle.sol";

contract CashOutOracle is ACashOutOracle, Named("cash-out-oracle"), Mortal, Component {

    constructor(address _config) Component(_config) public {}

    function onNextValidateCashOut(uint256 _commandId, uint256 _sessionId, uint256 _toWithdraw, uint256[] _bills) public {
        emit ValidateCashOut(_commandId, _sessionId, _toWithdraw, _bills);
    }

    /*function successValidate(uint256 _commandId) public onlyOwner {
    }

    function failValidate(uint256 _commandId) public onlyOwner {
    }*/

    function onNextCloseCashOut(uint256 _commandId, uint256 _sessionId, uint256 _toWithdraw, uint256[] _bills) public {
        emit CloseCashOut(_commandId, _sessionId, _toWithdraw, _bills);
    }

    /*function successClose(uint256 _commandId) public {
    }

    function failClose(uint256 _commandId) public {
    }*/

}