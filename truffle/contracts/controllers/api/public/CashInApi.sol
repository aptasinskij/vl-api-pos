pragma solidity 0.4.24;

interface CashInApi {

    // @formatter:off
    function openCashInChannel(
        uint256 _sessionId,
        uint256 _maxBalance,
        function(uint256, uint256) external _success,
        function(uint256, uint256, uint256) external _update,
        function(uint256) external _fail
    )
        external
        returns (
            bool _accepted
        );
    // @formatter:on

    // @formatter:off
    function closeCashInChannel(
        uint256 _sessionId,
        uint256 _channelId,
        uint256[] _fees,
        address[] _parties,
        function(uint256, uint256) external _success,
        function(uint256, uint256) external _fail
    )
        external
        returns (
            bool _accepted
        );
    // @formatter:on

    // @formatter:off
    function balanceOf(
        uint256 _channelId
    )
        external
        view
        returns (
            uint256 _balance
        );
    // @formatter:on
}
