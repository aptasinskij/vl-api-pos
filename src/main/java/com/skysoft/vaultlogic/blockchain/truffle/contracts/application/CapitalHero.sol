pragma solidity 0.4.24;

import "./IApplication.sol";
import "./RegistryDependent.sol";
import "../managers/Managers.sol";
import {ACashInController} from "../controllers/Controllers.sol";

contract CapitalHero is IApplication, RegistryDependent {

    string constant CASH_IN_CONTROLLER = "cash-in-controller";
    string constant SESSION_MANAGER = "session-manager";

    event CashInOpened(uint256 channelId, uint256 sessionId);
    event CashInClosed(uint256 channelId, uint256 sessionId);
    event CashInBalanceUpdated(uint256 channelId, uint256 balance, uint256 sessionId);
    event SessionCreated(uint256 timestamp);
    event SessionClosed(uint256 timestamp, uint256 sessionId);

    constructor(address regAddr) RegistryDependent(regAddr) public {}

    function openCashInChannel(uint256 sessionId) external {
        ACashInController(componentForName(CASH_IN_CONTROLLER)).open(sessionId);
    }

    function closeCashInChannel(uint256 sessionId, uint256 channelId, uint256[] _fees, address[] _parties) external {
        ACashInController(componentForName(CASH_IN_CONTROLLER)).close(sessionId, channelId, _fees, _parties);
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