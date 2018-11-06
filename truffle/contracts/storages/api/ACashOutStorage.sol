pragma solidity 0.4.24;

contract ACashOutStorage {

    event CashOutSaved(uint256 channelId, string kioskId, uint256 sessionId, address application, uint256 status);
    event CashOutStatusUpdated(uint256 channelId, uint256 status);
    event CashOutSplitAdded(uint256 channelId, address party, uint256 amount);

    function save(string kioskId, uint256 sessionId, address application, uint256 status, uint256 vaultLogicPercent,
        uint256 vaultLogicAmount, uint256 withdrawAmount, uint256 reservedAmount, address[] parties, uint256[] fees) public
    returns (uint256);

    function get(uint256 channelId) public view
    returns (
        string kioskId,
        uint256 sessionId,
        address application,
        uint256 status,
        uint256 vaultLogicPercent,
        uint256 vaultLogicAmount,
        uint256 withdrawAmount,
        uint256 reservedAmount,
        uint256 splitSize
    );

    function getKioskId(uint256 channelId) public view returns (string);

    function getSessionId(uint256 channelId) public view returns (uint256);

    function getApplication(uint256 channelId) public view returns (address);

    function setStatus(uint256 channelId, uint256 status) public;

    function getStatus(uint256 channelId) public view returns (uint256);

    function getWithdrawAmount(uint256 channelId) public view returns (uint256);

    function getReservedAmount(uint256 channelId) public view returns (uint256);

    function getVaultLogicAmount(uint256 channelId) public view returns (uint256);

    function setVaultLogicPercent(uint256 channelId, uint256 vaultLogicPercent) public;

    function getVaultLogicPercent(uint256 channelId) public view returns (uint256);

    function addSplit(uint256 channelId, address party, uint256 amount) public;

    function addSplits(uint256 channelId, address[] parties, uint256[] amounts) public;

    function getSplitSize(uint256 channelId) public view returns (uint256);

    function getSplit(uint256 channelId, uint256 subIndex) public view returns (address, uint256);

}
