pragma solidity 0.4.24;

interface CashOutApi {

    // @formatter:off
    function openCashOutChannel(
        uint256 _sessionId,
        uint256 _amount,
        uint256[] _bills,
        uint256[] _amounts,
        uint256[] _fees,
        address[] _parties,
        function(uint256) external _fail,
        function(uint256, uint256) external _success
    )
        external;
    // @formatter:on

    // @formatter:off
    function closeCashOutChannel(
        uint256 _sessionId,
        uint256 _channelId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
)
        external;
    // @formatter:on

}
