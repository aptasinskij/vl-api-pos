pragma solidity 0.4.24;

contract ACashOutManager {

    function openCashOutChannel(
        address _application,
        uint256 _sessionId,
        uint256 _amount,
        uint256[] _bills,
        uint256[] _amounts,
        uint256[] _fees,
        address[] _parties,
        function(uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public;

    function closeCashOutChannel(
        address _application,
        uint256 _sessionId,
        uint256 _channelId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
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

}