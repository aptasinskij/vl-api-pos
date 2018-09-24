pragma solidity 0.4.24;

contract ICashInStorage {
    
    function save(uint256 sessionId, address application, uint256 status) public returns(uint256);

    function get(uint256 channelId) external view
    returns(
        uint256 sessionId,
        address application,
        uint256 balance,
        uint256 status, 
        uint256 splitSize
    );

    function getSessionId(uint256 channelId) public view returns(uint256);

    function getApplication(uint256 channelId) public view returns(address);

    function getApplicationAndSessionId(uint256 channelId) public view returns(address, uint256);

    function setBalance(uint256 channelId, uint256 amount) public;

    function getBalance(uint256 channelId) public view returns(uint256);

    function setVLFee(uint256 channelId, uint256 fee) public;

    function getVLFee(uint256 channelId) public view returns(uint256);

    function setApplicationBalance(uint256 channelId, uint256 balance) public;

    function getApplicationBalance(uint256 channelId) public view returns(uint256);

    function setStatus(uint256 channelId, uint256 status) public;

    function getStatus(uint256 channelId) public view returns(uint256);

    function addSplit(uint256 channelId, address party, uint256 amount) public;

    function addSplits(uint256 channelId, address[] parties, uint256[] amounts) public;

    function getSplitSize(uint256 channelId) public view returns(uint256);

    function getSplit(uint256 channelId, uint256 subIndex) public view returns(address, uint256);

}