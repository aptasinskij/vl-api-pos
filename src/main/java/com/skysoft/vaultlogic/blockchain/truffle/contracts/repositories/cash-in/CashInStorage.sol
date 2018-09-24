pragma solidity 0.4.24;

import "./CashInLib.sol";
import "../../registry/RegistryComponent.sol";

contract CashInStorage is RegistryComponent {

    string constant COMPONENT_NAME = "cash-in-storage";
    string constant DATABASE = "database";

    event CashInSaved(uint256 channelId, uint256 sessionId, address application, uint256 status);
    event CashInBalanceUpdated(uint256 channelId, uint256 amount);
    event CashInStatusUpdated(uint256 channelId, uint256 status);
    event CashInSplitAdded(uint256 channelId, address party, uint256 amount);

    using CashInLib for address;

    constructor(address registryAddr) RegistryComponent(registryAddr) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function save(uint256 sessionId, address application, uint256 status) external returns(uint256 channelId) {
        channelId = lookup(DATABASE).save(sessionId, application, status);
        emit CashInSaved(channelId, sessionId, application, status);
    }

    function get(uint256 channelId) external view
    returns(
        uint256 sessionId,
        address application,
        uint256 balance,
        uint256 status,
        uint256 splitSize
    ) {
        return lookup(DATABASE).get(channelId);
    }

    function getSessionId(uint256 channelId) external view returns(uint256) {
        return lookup(DATABASE).getSessionId(channelId);
    }

    function getApplication(uint256 channelId) external view returns(address) {
        return lookup(DATABASE).getApplication(channelId);
    }

    function getApplicationAndSessionId(uint256 channelId) external view returns(address application, uint256 sessionId) {
        application = lookup(DATABASE).getApplication(channelId);
        sessionId = lookup(DATABASE).getSessionId(channelId);
    }

    function setBalance(uint256 channelId, uint256 amount) external {
        lookup(DATABASE).setBalance(channelId, amount);
        emit CashInBalanceUpdated(channelId, amount);
    }

    function getBalance(uint256 channelId) external view returns(uint256) {
        return lookup(DATABASE).getBalance(channelId);
    }

    function setVLFee(uint256 channelId, uint256 fee) external {
        lookup(DATABASE).setVLFee(channelId, fee);
    }

    function getVLFee(uint256 channelId) external view returns(uint256){
        lookup(DATABASE).getVLFee(channelId);
    }

    function setApplicationBalance(uint256 channelId, uint256 balance) public {
        lookup(DATABASE).setApplicationBalance(channelId, balance);
    }

    function getApplicationBalance(uint256 channelId) public view returns(uint256) {
        return lookup(DATABASE).getApplicationBalance(channelId);
    }

    function setStatus(uint256 channelId, uint256 status) external {
        lookup(DATABASE).setStatus(channelId, status);
        emit CashInStatusUpdated(channelId, status);
    }

    function getStatus(uint256 channelId) external view returns(uint256) {
        return lookup(DATABASE).getStatus(channelId);
    }

    function addSplit(uint256 channelId, address party, uint256 amount) external {
        lookup(DATABASE).addSplit(channelId, party, amount);
        emit CashInSplitAdded(channelId, party, amount);
    }

    function addSplits(uint256 channelId, address[] parties, uint256[] amounts) external {
        lookup(DATABASE).addSplits(channelId, parties, amounts);
    }

    function getSplitSize(uint256 channelId) external view returns(uint256) {
        return lookup(DATABASE).getSplitSize(channelId);
    }

    function getSplit(uint256 channelId, uint256 subIndex) external view returns(address, uint256) {
        return lookup(DATABASE).getSplit(channelId, subIndex);
    }

}