pragma solidity 0.4.24;

contract ACashOutOracle {

    event OpenCashOut(uint256 _commandId);
    event ValidateCashOut(uint256 _commandId, uint256 _sessionId, uint256 _toWithdraw, uint256[] _bills);
    event CloseCashOut(uint256 _commandId, uint256 _sessionId, uint256 _toWithdraw, uint256[] _bills);
    event RollbackCashOut(uint256 _commandId);

    function onNextOpenCashOut(uint256 _commandId) public;

    function successOpen(uint256 _commandId) public;

    function failOpen(uint256 _commandId) public;

    function onNextValidateCashOut(uint256 _commandId, uint256 _sessionId, uint256 _toWithdraw, uint256[] _bills) public;

    function successValidate(uint256 _commandId) public;

    function failValidate(uint256 _commandId) public;

    function onNextCloseCashOut(uint256 _commandId, uint256 _sessionId, uint256 _toWithdraw, uint256[] _bills) public;

    function successClose(uint256 _commandId) public;

    function failClose(uint256 _commandId) public;

    function onNextRollbackCashOut(uint256 _commandId) public;

    function successRollback(uint256 _commandId) public;

    function failRollback(uint256 _commandId) public;

}
