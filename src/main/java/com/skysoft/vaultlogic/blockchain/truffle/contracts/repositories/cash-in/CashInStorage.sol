pragma solidity 0.4.24;

import "./CashInLib.sol";
import "../../registry/RegistryComponent.sol";

contract CashInRepository is RegistryComponent {

    string constant COMPONENT_NAME = "cash-in-storage";
    string constant DATABASE = "database";

    event CashInSaved(uint256 sessionId, address application, uint256 status, uint256 index);
    event CashInBalanceUpdated(uint256 index, uint256 amount);
    event CashInStatusUpdated(uint256 index, uint256 status);
    event CahsInSplitAdded(uint256 index, address party, uint256 amount);

    using CashInLib for address;

    constructor(address registryAddr) RegistryComponent(registryAddr) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function save(uint256 sessionId, address application, uint256 status) external returns(uint256 index) {
        index = lookup(DATABASE).save(sessionId, application, status);
        emit CashInSaved(sessionId, application, status, index);
    }

    function get(uint256 index) external view 
    returns(
        uint256 sessionId,
        address application,
        uint256 balance,
        uint256 status,
        uint256 splitSize
    ) {
        return lookup(DATABASE).get(index);
    }

    function getSessionId(uint256 index) external view returns(uint256) {
        return lookup(DATABASE).getSessionId(index);
    }

    function getApplication(uint256 index) external view returns(address) {
        return lookup(DATABASE).getApplication(index);
    }

    function getApplicationAndSessionId(uint256 index) external view returns(address application, uint256 sessionId) {
        application = lookup(DATABASE).getApplication(index);
        sessionId = lookup(DATABASE).getSessionId(index);
    }

    function setBalance(uint256 index, uint256 amount) external {
        lookup(DATABASE).setBalance(index, amount);
        emit CashInBalanceUpdated(index, amount);
    }

    function getBalance(uint256 index) external view returns(uint256) {
        return lookup(DATABASE).getBalance(index);
    }

    function setStatus(uint256 index, uint256 status) external {
        lookup(DATABASE).setStatus(index, status);
        emit CashInStatusUpdated(index, status);
    }

    function getStatus(uint256 index) external view returns(uint256) {
        return lookup(DATABASE).getStatus(index);
    }

    function addSplit(uint256 index, address party, uint256 amount) external {
        lookup(DATABASE).addSplit(index, party, amount);
        emit CahsInSplitAdded(index, party, amount);
    }

    function addSplits(uint256 index, address[] parties, uint256[] amounts) external {
        lookup(DATABASE).addSplits(index, parties, amounts);
    }

    function getSplitSize(uint256 index) external view returns(uint256) {
        return lookup(DATABASE).getSplitSize(index);
    }

    function getSplit(uint256 index, uint256 subIndex) external view returns(address, uint256) {
        return lookup(DATABASE).getSplit(index, subIndex);
    }

}