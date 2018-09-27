pragma solidity 0.4.24;

interface IApplication {
    
    function openCashInChannel(uint256 sessionId) external;

    function closeCashInChannel(uint256 sessionId, uint256 channelId, uint256[] _fees, address[] _parties) external;

    function cashInBalanceUpdate(uint256 channelId, uint256 balance, uint256 sessionId) external;

    function cashInChannelOpened(uint256 channelId, uint256 sessionId) external;

    function cashInChannelClosed(uint256 channelId, uint256 sessionId) external;

    function newSessionCreated() external;

    function closeSession(uint256 sessionId) external;

    function sessionClosed(uint256 sessionId) external;

}