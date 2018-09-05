pragma solidity 0.4.24;

interface CashAcceptorOracleApi {
    
    function open(string xToken, uint256 sessionId, uint256 channelId) external;

    function close(string xToken, uint256 sessionId, uint256 channelId) external;

    function confirmOpen(uint256 channelId) external;

    function confirmClose(uint256 channelId) external;

    function increaseBalance(uint256 channelId, uint256 amount) external;

}