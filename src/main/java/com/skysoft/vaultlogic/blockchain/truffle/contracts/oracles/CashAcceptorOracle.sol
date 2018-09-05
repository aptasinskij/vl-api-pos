pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";
import "../services/CashChannelsServiceApi.sol";

contract CashAcceptorOracle is RegistryComponent {

    string constant CASH_ACCEPTOR_ORACLE = "cash-acceptor-oracle";

    string constant CASH_CHANNELS_SERVICE = "cash-channels-service";
    
    event OpenCashAcceptor(string xToken, uint256 sessionId, uint256 channelId);
    event CloseCashAcceptor(string xToken, uint256 index, uint256 channelId);

    constructor(address regAddr) RegistryComponent(regAddr) public {}

    function getName() internal pure returns(string name) {
        return CASH_ACCEPTOR_ORACLE;
    }

    function open(string xToken, uint256 sessionId, uint256 channelId) external {
        emit OpenCashAcceptor(xToken, sessionId, channelId);    
    }

    function close(string xToken, uint256 sessionId, uint256 channelId) external {
        emit CloseCashAcceptor(xToken, sessionId, channelId);
    }

    function confirmOpen(uint256 channelId) external {
        CashChannelsServiceApi(lookup(CASH_CHANNELS_SERVICE)).confirmOpen(channelId);
    }

    function confirmClose(uint256 channelId) external {
        CashChannelsServiceApi(lookup(CASH_CHANNELS_SERVICE)).confirmClose(channelId);
    }

}