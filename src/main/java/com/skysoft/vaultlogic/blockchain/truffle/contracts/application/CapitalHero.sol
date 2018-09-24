pragma solidity 0.4.24;

import "./IApplication.sol";
import "./RegistryDependent.sol";
import "../services/ACashChannelsManager.sol";
import "../services/ASessionManager.sol";

contract CapitalHero is IApplication, RegistryDependent {

    string constant CASH_CHANNELS_MANAGER = "cash-channels-manager";
    string constant SESSION_MANAGER = "session-manager";

    event CashInOpened(uint256 channelId, uint256 sessionId);
    event CashInClosed(uint256 channelId, uint256 sessionId);
    event CashInBalanceUpdated(uint256 channelId, uint256 balance, uint256 sessionId);
    event SessionCreated(uint256 timestamp);
    event SessionClosed(uint256 timestamp, uint256 sessionId);

    constructor(address regAddr) RegistryDependent(regAddr) public {}

    function openCashInChannel(uint256 sessionId) external {
        ACashChannelsManager(componentForName(CASH_CHANNELS_MANAGER)).openCashInChannel(address(this), sessionId);
    }

    function closeCashInChannel(uint256 sessionId, uint256 channelId) external {
        /*ICashChannelsManager(componentForName(CASH_CHANNELS_MANAGER)).closeCashInChannel(sessionId, channelId);*/
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
        ASessionManager(componentForName(SESSION_MANAGER)).closeSession(sessionId);
    }

    function sessionClosed(uint256 sessionId) external {
        emit SessionClosed(now, sessionId);
    }
    
}