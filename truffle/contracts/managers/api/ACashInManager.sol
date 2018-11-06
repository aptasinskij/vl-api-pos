pragma solidity 0.4.24;

contract ACashInManager {

    function openCashInChannel(
        address _application,
        uint256 _sessionId,
        uint256 _maxBalance,
        function(uint256) external _fail,
        function(uint256, uint256) external _success,
        function(uint256, uint256, uint256) external _update
    )
        public;

    function closeCashInChannel(
        address _application,
        uint256 _sessionId,
        uint256 _channelId,
        uint256[] _fees,
        address[] _parties,
        function(uint256, uint256) external _success,
        function(uint256, uint256) external _fail
    )
        public;

    function confirmOpen(
        uint256 channelId
    )
        public;

    function confirmClose(
        uint256 channelId
    )
        public;

    function updateCashInBalance(
        uint256 channelId,
        uint256 amount
    )
        public;

    function balanceOf(
        address _application,
        uint256 _channelId
    )
        public
        view
        returns (
            uint256 _balance
        );

}
