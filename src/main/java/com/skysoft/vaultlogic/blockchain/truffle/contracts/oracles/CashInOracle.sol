pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../services/ICashChannelsManager.sol";

contract CashInOracle is RegistryComponent {

    string constant COMPONENT_NAME = "cash-in-oracle";
    
    event OpenCashAcceptor(uint256 sessionId, uint256 channelId, uint256 channelStatus);
    event CloseCashAcceptor(uint256 sessionId, uint256 channelId);

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string name) {
        return COMPONENT_NAME;
    }

    function open(uint256 sessionId, uint256 channelId, uint256 channelStatus) external {
        emit OpenCashAcceptor(sessionId, channelId, channelStatus);
    }

    function close(uint256 sessionId, uint256 channelId) external {
        emit CloseCashAcceptor(sessionId, channelId);
    }

    function confirmOpen(uint256 channelId) external {
        _cashChannelsManager().confirmOpen(channelId);
    }

    function confirmClose(uint256 channelId) external {
        _cashChannelsManager().confirmClose(channelId);
    }

    function increaseBalance(uint256 channelId, uint256 amount) external {
        _cashChannelsManager().updateCashInBalance(channelId, amount);
    }

}