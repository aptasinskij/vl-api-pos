pragma solidity 0.4.24;

contract ACashOutOracle {

    event ValidateCashOut(uint256 _commandId, uint256 _sessionId, uint256 _toWithdraw, uint256[] _bills);
    event CloseCashOut(uint256 _commandId, uint256 _sessionId, uint256 _toWithdraw, uint256[] _bills);

    function onNextValidateCashOut(uint256 _commandId, uint256 _sessionId, uint256 _toWithdraw, uint256[] _bills) public;

    /*function successValidate(uint256 _commandId) public;

    function failValidate(uint256 _commandId) public;*/

    function onNextCloseCashOut(uint256 _commandId, uint256 _sessionId, uint256 _toWithdraw, uint256[] _bills) public;

    /*function successClose(uint256 _commandId) public;

    function failClose(uint256 _commandId) public;*/

}
