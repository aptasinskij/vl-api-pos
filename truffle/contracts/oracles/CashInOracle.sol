pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "../managers/api/ACashInManager.sol";
import "./api/ACashInOracle.sol";

contract CashInOracle is ACashInOracle, Named("cash-in-oracle"), Mortal, Component {

    string constant MANAGER = "cash-in-manager";

    constructor(address _config) Component(_config) public {}

    function onNextOpenCashIn(uint256 _commandId, uint256 _sessionId, uint256 _maxBalance) public {
        emit OpenCashIn(_commandId, _sessionId, _maxBalance);
    }

    function successOpen(uint256 _commandId) public onlyOwner {
        ACashInManager(context.get(MANAGER)).confirmOpen(_commandId);
    }

    function increaseBalance(uint256 channelId, uint256 amount) public {
        ACashInManager(context.get(MANAGER)).updateCashInBalance(channelId, amount);
    }

    function failOpen(uint256 _commandId) public onlyOwner {
        //TODO:implementation: add ACashInManager::confirmFailOpen(uint256 _commandId) call
    }

    function onNextCloseCashIn(uint256 _commandId, uint256 _sessionId) public {
        emit CloseCashIn(_commandId, _sessionId);
    }

    function successClose(uint256 _commandId) public {
        ACashInManager(context.get(MANAGER)).confirmClose(_commandId);
    }

    function failClose(uint256 _commandId) public {
        //TODO:implementation: add ACashInManager::confirmFailClose(uint256 _commandId) call
    }

}