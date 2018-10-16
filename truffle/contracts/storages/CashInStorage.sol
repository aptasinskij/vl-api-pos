pragma solidity 0.4.24;

import {CashInLib} from "../libs/Libraries.sol";
import {ACashInStorage} from "./Storages.sol";
import "../Platform.sol";

contract CashInStorage is ACashInStorage, Named("cash-in-storage"), Mortal, Component {

    using CashInLib for address;

    constructor(address _config) Component(_config) public {}

    function save(uint256 sessionId, address application, uint256 status) public returns(uint256 channelId) {
        channelId = database.save(sessionId, application, status);
        emit CashInSaved(channelId, sessionId, application, status);
    }

    function get(uint256 channelId) public view
    returns(
        uint256 sessionId,
        address application,
        uint256 balance,
        uint256 status,
        uint256 splitSize
    ) {
        return database.get(channelId);
    }

    function getSessionId(uint256 channelId) public view returns(uint256) {
        return database.getSessionId(channelId);
    }

    function getApplication(uint256 channelId) public view returns(address) {
        return database.getApplication(channelId);
    }

    function getApplicationAndSessionId(uint256 channelId) public view returns(address application, uint256 sessionId) {
        application = database.getApplication(channelId);
        sessionId = database.getSessionId(channelId);
    }

    function setBalance(uint256 channelId, uint256 amount) public {
        database.setBalance(channelId, amount);
        emit CashInBalanceUpdated(channelId, amount);
    }

    function getBalance(uint256 channelId) public view returns(uint256) {
        return database.getBalance(channelId);
    }

    function setVLFee(uint256 channelId, uint256 fee) public {
        database.setVLFee(channelId, fee);
    }

    function getVLFee(uint256 channelId) public view returns(uint256){
        return database.getVLFee(channelId);
    }

    function setApplicationBalance(uint256 channelId, uint256 balance) public {
        database.setApplicationBalance(channelId, balance);
    }

    function getApplicationBalance(uint256 channelId) public view returns(uint256) {
        return database.getApplicationBalance(channelId);
    }

    function setStatus(uint256 channelId, uint256 status) public {
        database.setStatus(channelId, status);
        emit CashInStatusUpdated(channelId, status);
    }

    function getStatus(uint256 channelId) public view returns(uint256) {
        return database.getStatus(channelId);
    }

    function addSplit(uint256 channelId, address party, uint256 amount) public {
        database.addSplit(channelId, party, amount);
        emit CashInSplitAdded(channelId, party, amount);
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