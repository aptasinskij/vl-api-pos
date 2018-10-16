pragma solidity 0.4.24;

import "../registry/Component.sol";
import {CashOutLib} from "../libs/Libraries.sol";
import {ACashOutStorage} from "./Storages.sol";

contract CashInStorage is Component, ACashOutStorage {

    string constant COMPONENT_NAME = "cash-out-storage";
    string constant DATABASE = "database";

    using CashOutLib for address;

    constructor(address registryAddr) Component(registryAddr) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function save(string kioskId, uint256 sessionId, address application, uint256 status, uint256 vaultLogicPercent,
        uint256 vaultLogicAmount, uint256 withdrawAmount, uint256 reservedAmount, address[] parties, uint256[] fees) public returns (uint256 channelId) {
        channelId = lookup(DATABASE).save(kioskId, sessionId, application, status, vaultLogicPercent, vaultLogicAmount, withdrawAmount, reservedAmount, parties, fees);
        emit CashOutSaved(channelId, kioskId, sessionId, application, status);
    }

    function get(uint256 channelId) public view
    returns(
        string kioskId,
        uint256 sessionId,
        address application,
        uint256 status,
        uint256 vaultLogicPercent,
        uint256 vaultLogicAmount,
        uint256 withdrawAmount,
        uint256 reservedAmount,
        uint256 splitSize
    ) {
        return lookup(DATABASE).get(channelId);
    }

    function getKioskId(uint256 channelId) public view returns(string) {
        return lookup(DATABASE).getKioskId(channelId);
    }

    function getSessionId(uint256 channelId) public view returns(uint256) {
        return lookup(DATABASE).getSessionId(channelId);
    }

    function getApplication(uint256 channelId) public view returns(address) {
        return lookup(DATABASE).getApplication(channelId);
    }

    function setStatus(uint256 channelId, uint256 status) public {
        lookup(DATABASE).setStatus(channelId, status);
        emit CashOutStatusUpdated(channelId, status);
    }

    function getStatus(uint256 channelId) public view returns(uint256) {
        return lookup(DATABASE).getStatus(channelId);
    }

    function getWithdrawAmount(uint256 channelId) public view returns(uint256) {
        return lookup(DATABASE).getWithdrawAmount(channelId);
    }

    function getReservedAmount(uint256 channelId) public view returns(uint256) {
        return lookup(DATABASE).getReservedAmount(channelId);
    }

    function getVaultLogicAmount(uint256 channelId) public view returns(uint256) {
        return lookup(DATABASE).getVaultLogicAmount(channelId);
    }

    function setVaultLogicPercent(uint256 channelId, uint256 vaultLogicPercent) public {
        lookup(DATABASE).setVaultLogicPercent(channelId, vaultLogicPercent);
    }

    function getVaultLogicPercent(uint256 channelId) public view returns(uint256){
        return lookup(DATABASE).getVaultLogicPercent(channelId);
    }

    function addSplit(uint256 channelId, address party, uint256 amount) public {
        lookup(DATABASE).addSplit(channelId, party, amount);
        emit CashOutSplitAdded(channelId, party, amount);
    }

    function addSplits(uint256 channelId, address[] parties, uint256[] amounts) public {
        lookup(DATABASE).addSplits(channelId, parties, amounts);
    }

    function getSplitSize(uint256 channelId) public view returns(uint256) {
        return lookup(DATABASE).getSplitSize(channelId);
    }

    function getSplit(uint256 channelId, uint256 subIndex) public view returns(address, uint256) {
        return lookup(DATABASE).getSplit(channelId, subIndex);
    }

}