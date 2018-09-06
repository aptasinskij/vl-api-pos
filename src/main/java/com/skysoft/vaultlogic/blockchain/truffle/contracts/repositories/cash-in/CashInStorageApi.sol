pragma solidity 0.4.24;

interface CashInStorageApi {
    
    function save(uint256 sessionId, address application, uint256 status) external returns(uint256);

    function get(uint256 index) external view 
    returns(
        uint256 sessionId,
        address application,
        uint256 balance,
        uint256 status, 
        uint256 splitSize
    );

    function getSessionId(uint256 index) external view returns(uint256);

    function getApplication(uint256 index) external view returns(address);

    function getApplicationAndSessionId(uint256 index) external view returns(address, uint256);

    function setBalance(uint256 index, uint256 amount) external;

    function getBalance(uint256 index) external view returns(uint256);

    function setStatus(uint256 index, uint256 status) external;

    function getStatus(uint256 index) external view returns(uint256);

    function addSplit(uint256 index, address party, uint256 amount) external;

    function addSplits(uint256 index, address[] parties, uint256[] amounts) external;

    function getSplitSize(uint256 index) external view returns(uint256);

    function getSplit(uint256 index, uint256 subIndex) external view returns(address, uint256);

}