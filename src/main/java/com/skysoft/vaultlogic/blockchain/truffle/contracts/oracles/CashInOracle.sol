pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../services/ICashChannelsManager.sol";

contract CashInOracle is RegistryComponent {

    string constant COMPONENT_NAME = "cash-in-oracle";

    string constant CASH_CHANNELS_MANAGER = "cash-channels-manager";
    
    event OpenCashAcceptor(uint256 sessionId, uint256 channelId, uint256 channelStatus);
    event CloseCashAcceptor(string xToken, uint256 index, uint256 channelId);

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string name) {
        return COMPONENT_NAME;
    }

    function open(uint256 sessionId, uint256 channelId, uint256 channelStatus) external {
        emit OpenCashAcceptor(sessionId, channelId, channelStatus);
    }

    function close(string xToken, uint256 sessionId, uint256 channelId) external {
        emit CloseCashAcceptor(xToken, sessionId, channelId);
    }

    function confirmOpen(uint256 channelId) external {
        ICashChannelsManager(lookup(CASH_CHANNELS_MANAGER)).confirmOpen(channelId);
    }

    function confirmClose(uint256 channelId) external {
        ICashChannelsManager(lookup(CASH_CHANNELS_MANAGER)).confirmClose(channelId);
    }

    function increaseBalance(uint256 channelId, uint256 amount) external {
        ICashChannelsManager(lookup(CASH_CHANNELS_MANAGER)).updateCashInBalance(channelId, amount);
    }

}