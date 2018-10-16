pragma solidity 0.4.24;

import {ACashChannelsManager} from "../managers/Managers.sol";
import {ACashInOracle} from "./Oracles.sol";
import "../Platform.sol";

contract CashInOracle is ACashInOracle, Named("cash-in-oracle"), Mortal, Component {

    string constant MANAGER = "cash-channels-manager";

    constructor(address _config) Component(_config) public {}

    function open(uint256 sessionId, uint256 channelId, uint256 channelStatus) public {
        emit OpenCashAcceptor(sessionId, channelId, channelStatus);
    }

    function close(uint256 sessionId, uint256 channelId) public {
        emit CloseCashAcceptor(sessionId, channelId);
    }

    function confirmOpen(uint256 channelId) public {
        ACashChannelsManager(context.get(MANAGER)).confirmOpen(channelId);
    }

    function confirmClose(uint256 channelId) public {
        ACashChannelsManager(context.get(MANAGER)).confirmClose(channelId);
    }

    function increaseBalance(uint256 channelId, uint256 amount) public {
        ACashChannelsManager(context.get(MANAGER)).updateCashInBalance(channelId, amount);
    }

}