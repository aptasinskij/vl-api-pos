pragma solidity 0.4.24;

interface ICashInStorage {
    
    function save(uint256 sessionId, address application, uint256 status) external returns(uint256);

    function get(uint256 channelId) external view
    returns(
        uint256 sessionId,
        address application,
        uint256 balance,
        uint256 status, 
        uint256 splitSize
    );

    function getSessionId(uint256 channelId) external view returns(uint256);

    function getApplication(uint256 channelId) external view returns(address);

    function getApplicationAndSessionId(uint256 channelId) external view returns(address, uint256);

    function setBalance(uint256 channelId, uint256 amount) external;

    function getBalance(uint256 channelId) external view returns(uint256);

    function setStatus(uint256 channelId, uint256 status) external;

    function getStatus(uint256 channelId) external view returns(uint256);

    function addSplit(uint256 channelId, address party, uint256 amount) external;

    function addSplits(uint256 channelId, address[] parties, uint256[] amounts) external;

    function getSplitSize(uint256 channelId) external view returns(uint256);

    function getSplit(uint256 channelId, uint256 subIndex) external view returns(address, uint256);

}