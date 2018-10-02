pragma solidity 0.4.24;

interface ICashInOracle {
    
    function open(uint256 sessionId, uint256 channelId, uint256 channelStatus) external;

    function close(uint256 sessionId, uint256 channelId) external;

    function confirmOpen(uint256 channelId) external;

    function confirmClose(uint256 channelId) external;

    function increaseBalance(uint256 channelId, uint256 amount) external;

}