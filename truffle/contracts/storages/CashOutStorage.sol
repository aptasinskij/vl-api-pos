pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/ACashOutStorage.sol";
import {CashOutLib} from "../libs/Libraries.sol";

contract CashInStorage is ACashOutStorage, Named("cash-out-storage"), Mortal, Component {

    string constant DATABASE = "database";

    using CashOutLib for address;

    constructor(address _config) Component(_config) public {}

    function save(string kioskId, uint256 sessionId, address application, uint256 status, uint256 vaultLogicPercent,
        uint256 vaultLogicAmount, uint256 withdrawAmount, uint256 reservedAmount, address[] parties, uint256[] fees) public returns (uint256 channelId) {
        channelId = database.save(kioskId, sessionId, application, status, vaultLogicPercent, vaultLogicAmount, withdrawAmount, reservedAmount, parties, fees);
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
        return database.get(channelId);
    }

    function getKioskId(uint256 channelId) public view returns(string) {
        return database.getKioskId(channelId);
    }

    function getSessionId(uint256 channelId) public view returns(uint256) {
        return database.getSessionId(channelId);
    }

    function getApplication(uint256 channelId) public view returns(address) {
        return database.getApplication(channelId);
    }

    function setStatus(uint256 channelId, uint256 status) public {
        database.setStatus(channelId, status);
        emit CashOutStatusUpdated(channelId, status);
    }

    function getStatus(uint256 channelId) public view returns(uint256) {
        return database.getStatus(channelId);
    }

    function getWithdrawAmount(uint256 channelId) public view returns(uint256) {
        return database.getWithdrawAmount(channelId);
    }

    function getReservedAmount(uint256 channelId) public view returns(uint256) {
        return database.getReservedAmount(channelId);
    }

    function getVaultLogicAmount(uint256 channelId) public view returns(uint256) {
        return database.getVaultLogicAmount(channelId);
    }

    function setVaultLogicPercent(uint256 channelId, uint256 vaultLogicPercent) public {
        database.setVaultLogicPercent(channelId, vaultLogicPercent);
    }

    function getVaultLogicPercent(uint256 channelId) public view returns(uint256){
        return database.getVaultLogicPercent(channelId);
    }

    function addSplit(uint256 channelId, address party, uint256 amount) public {
        database.addSplit(channelId, party, amount);
        emit CashOutSplitAdded(channelId, party, amount);
    }

    function addSplits(uint256 channelId, address[] parties, uint256[] amounts) public {
        database.addSplits(channelId, parties, amounts);
    }

    function getSplitSize(uint256 channelId) public view returns(uint256) {
        return database.getSplitSize(channelId);
    }

    function getSplit(uint256 channelId, uint256 subIndex) public view returns(address, uint256) {
        return database.getSplit(channelId, subIndex);
    }

}