pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../services/ICashChannelsManager.sol";

contract CashInOracle is RegistryComponent {

    string constant COMPONENT_NAME = "cash-in-oracle";

    string constant CASH_CHANNELS_MANAGER = "cash-channels-manager";
    
    event OpenCashAcceptor(string xToken, uint256 sessionId, uint256 channelId);
    event CloseCashAcceptor(string xToken, uint256 index, uint256 channelId);

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string name) {
        return COMPONENT_NAME;
    }

    function open(string xToken, uint256 sessionId, uint256 channelId) external {
        emit OpenCashAcceptor(xToken, sessionId, channelId);    
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

}