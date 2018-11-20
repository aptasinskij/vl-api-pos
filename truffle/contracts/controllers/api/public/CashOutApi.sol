pragma solidity 0.4.24;

contract CashOutApi {

    // @formatter:off
    function openCashOutChannel(
        string _requestId,
        string _kioskId,
        uint256 _toWithdraw,
        uint256[] _fees,
        address[] _parties,
        function(string memory, string memory) external _fail,
        function(string memory, string memory, uint256, uint256) external _success
    )
        public;
    // @formatter:on

    // @formatter:off
    function validateCashOutChannel(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public;
    // @formatter:on

    // @formatter:off
    function closeCashOutChannel(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public;
    // @formatter:on

    // @formatter:off
    function rollbackCashOutChannel(
        uint256 _cashOutId,
        function(uint256) external _fail,
        function(uint256) external _success
    )
        public;
    // @formatter:on

}