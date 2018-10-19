pragma solidity 0.4.24;

import {CashInOpenLib} from "../libs/Libraries.sol";
import {ACashChannelsManager} from "../managers/Managers.sol";
import {ACashInOracle} from "./Oracles.sol";
import "../Platform.sol";

contract CashInOracle is ACashInOracle, Named("cash-in-oracle"), Mortal, Component {

    using CashInOpenLib for address;

    string constant MANAGER = "cash-channels-manager";

    constructor(address _config) Component(_config) public {}

    function onNextOpenCashIn(uint256 _commandId) public returns (bool _accepted) {
        CashInOpenLib.OpenCashIn memory command = database.retrieveOpenCashIn(_commandId);
        emit OpenCashIn(_commandId, context.sessionId, command.cashInId, command.maxBalance);
        _accepted = true;
    }

    function successOpen(uint256 _commandId) public onlyOwner {
        ACashChannelsManager(context.get(MANAGER)).confirmOpen(_commandId);
    }

    function failOpen(uint256 _commandId) public onlyOwner {

    }

    function close(uint256 sessionId, uint256 channelId) public {
        emit CloseCashAcceptor(sessionId, channelId);
    }

    function confirmClose(uint256 channelId) public {
        ACashChannelsManager(context.get(MANAGER)).confirmClose(channelId);
    }

    function increaseBalance(uint256 channelId, uint256 amount) public {
        ACashChannelsManager(context.get(MANAGER)).updateCashInBalance(channelId, amount);
    }

}