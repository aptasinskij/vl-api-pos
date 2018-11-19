pragma solidity 0.4.24;

contract ACashOutManager {

    function openCashOutChannel(
        address _application,
        string _kioskId,
        uint256 _toWithdraw,
        uint256[] _fees,
        address[] _parties,
        function(string memory) external _fail,
        function(string memory, uint256) external _success
    )
        public;

    function validateCashOutChannel(
        address _application,
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public;

    function closeCashOutChannel(
        address _application,
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public;

    function rollbackCashOutChannel(
        address _application,
        uint256 _cashOutId,
        function(uint256) external _fail,
        function(uint256) external _success
    )
        public;

    function confirmOpen(
        uint256 _commandId
    )
        public;

    function confirmFailOpen(
        uint256 _commandId
    )
        public;

    function confirmValidate(
        uint256 _commandId
    )
        public;

    function confirmFailValidate(
        uint256 _commandId
    )
        public;

    function confirmClose(
        uint256 _commandId
    )
        public;

    function confirmFailClose(
        uint256 _commandId
    )
        public;

    function confirmRollback(
        uint256 _commandId
    )
        public;

    function confirmFailRollback(
        uint256 _commandId
    )
        public;

}