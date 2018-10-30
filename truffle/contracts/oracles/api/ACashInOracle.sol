pragma solidity 0.4.24;

contract ACashInOracle {

    event OpenCashAcceptor(uint256 sessionId, uint256 channelId, uint256 channelStatus, uint256 _maxAmount);
    event CloseCashAcceptor(uint256 sessionId, uint256 channelId);
    event OpenCashIn(uint256 _commandId, uint256 _sessionId, uint256 _maxBalance);

    //open cash-in functions
    function onNextOpenCashIn(uint256 _commandId) public returns (bool _accepted);

    function successOpen(uint256 _commandId) public;

    function increaseBalance(uint256 channelId, uint256 amount) public;

    function failOpen(uint256 _commandId) public;

    //close cash-in functions
    function onNextCloseCashIn(uint256 _commandId) public returns (bool _accepted);

    function successClose(uint256 _commandId) public;

    function failClose(uint256 _commandId) public;

}
