pragma solidity 0.4.24;

import {ACashChannelsManager} from "../managers/Managers.sol";
import {ACashInOracle} from "./Oracles.sol";

contract CashInOracle is ACashInOracle {

    string constant COMPONENT_NAME = "cash-in-oracle";
    
    event OpenCashAcceptor(uint256 sessionId, uint256 channelId, uint256 channelStatus);
    event CloseCashAcceptor(uint256 sessionId, uint256 channelId);

    constructor(address regAddr) ACashInOracle(regAddr) public {}

    function getName() internal pure returns(string name) {
        return COMPONENT_NAME;
    }

    function open(uint256 sessionId, uint256 channelId, uint256 channelStatus) public {
        emit OpenCashAcceptor(sessionId, channelId, channelStatus);
    }

    function close(uint256 sessionId, uint256 channelId) public {
        emit CloseCashAcceptor(sessionId, channelId);
    }

    function confirmOpen(uint256 channelId) public {
        ACashChannelsManager(_cashChannelsManager()).confirmOpen(channelId);
    }

    function confirmClose(uint256 channelId) public {
        ACashChannelsManager(_cashChannelsManager()).confirmClose(channelId);
    }

    function increaseBalance(uint256 channelId, uint256 amount) public {
        ACashChannelsManager(_cashChannelsManager()).updateCashInBalance(channelId, amount);
    }

}