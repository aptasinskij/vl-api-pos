pragma solidity 0.4.24;

interface CashChannelsServiceApi {
    
    function openCashInChannel(uint256 sessionId) external returns(uint256);

    function closeCashInChannel(uint256 sessionId, uint256 channelId) external;

    function confirmOpen(uint256 channelId) external;

    function confirmClose(uint256 channelId) external;

}