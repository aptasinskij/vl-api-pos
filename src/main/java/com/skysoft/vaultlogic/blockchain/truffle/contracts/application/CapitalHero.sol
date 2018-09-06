pragma solidity 0.4.24;

import "./ApplicationApi.sol";
import "./RegistryDependent.sol";
import "../services/CashChannelsManagerApi.sol";
import "../services/SessionServiceApi.sol";

contract CapitalHero is ApplicationApi, RegistryDependent {

    string constant CASH_CHANNELS_SERVICE = "cash-channels-manager";
    string constant SESSION_SERVICE = "session-service";

    event CashInOpened(uint256 channelId, uint256 sessionId);
    event CashInClosed(uint256 channelId, uint256 sessionId);
    event CashInBalanceUpdated(uint256 channelId, uint256 balance, uint256 sessionId);
    event SessionCreated(uint256 timestamp);
    event SessionClosed(uint256 timestamp, uint256 sessionId);

    constructor(address regAddr) RegistryDependent(regAddr) public {}

    function openCashInChannel(uint256 sessionId) external {
        CashChannelsOperatorApi(componentForName(CASH_CHANNELS_SERVICE)).openCashInChannel(sessionId);
    }

    function closeCashInChannel(uint256 sessionId, uint256 channelId) external {
        CashChannelsOperatorApi(componentForName(CASH_CHANNELS_SERVICE)).closeCashInChannel(sessionId, channelId);
    }

    function cashInChannelOpened(uint256 channelId, uint256 sessionId) external {
        emit CashInOpened(channelId, sessionId);
    }

    function cashInBalanceUpdate(uint256 channelId, uint256 balance, uint256 sessionId) external {
        emit CashInBalanceUpdated(channelId, balance, sessionId);
    }

    function cashInChannelClosed(uint256 channelId, uint256 sessionId) external {
        emit CashInClosed(channelId, sessionId);
    }

    function newSessionCreated() external {
        emit SessionCreated(now);
    }

    function closeSession(uint256 sessionId) external {
        SessionServiceApi(componentForName(SESSION_SERVICE)).closeSession(sessionId);
    }

    function sessionClosed(uint256 sessionId) external {
        emit SessionClosed(now, sessionId);
    }
    
}