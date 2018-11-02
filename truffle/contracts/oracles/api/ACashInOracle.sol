pragma solidity 0.4.24;

contract ACashInOracle {

    event OpenCashIn(uint256 _commandId, uint256 _sessionId, uint256 _maxBalance);
    event CloseCashIn(uint256 _cashInId, uint256 _sessionId);

    function onNextOpenCashIn(uint256 _commandId, uint256 _sessionId, uint256 _maxBalance) public;

    function successOpen(uint256 _commandId) public;

    function increaseBalance(uint256 channelId, uint256 amount) public;

    function failOpen(uint256 _commandId) public;

    function onNextCloseCashIn(uint256 _commandId, uint256 _sessionId) public;

    function successClose(uint256 _commandId) public;

    function failClose(uint256 _commandId) public;

}
