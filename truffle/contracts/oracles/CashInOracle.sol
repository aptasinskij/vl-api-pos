pragma solidity 0.4.24;

import "../libs/Libraries.sol";
import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "../managers/api/ACashInManager.sol";
import "./api/ACashInOracle.sol";

contract CashInOracle is ACashInOracle, Named("cash-in-oracle"), Mortal, Component {

    using CashInLib for address;

    string constant MANAGER = "cash-channels-manager";

    constructor(address _config) Component(_config) public {}

    //open cash-in functions
    function onNextOpenCashIn(uint256 _commandId) public returns (bool _accepted) {
        CashInLib.Open memory command = database.retrieveCashInOpen(_commandId);
        emit OpenCashIn(_commandId, command.sessionId, command.maxBalance);
        _accepted = true;
    }

    function successOpen(uint256 _commandId) public onlyOwner {
        ACashInManager(context.get(MANAGER)).confirmOpen(_commandId);
    }

    function increaseBalance(uint256 channelId, uint256 amount) public {
        ACashInManager(context.get(MANAGER)).updateCashInBalance(channelId, amount);
    }

    function failOpen(uint256 _commandId) public onlyOwner {

    }

    //close cash-in functions
    function onNextCloseCashIn(uint256 _commandId) public returns (bool _accepted) {
        CashInLib.Close memory command = database.retrieveCashInClose(_commandId);
        emit CloseCashAcceptor(command.sessionId, _commandId);
        _accepted = true;
    }

    function successClose(uint256 _commandId) {
        ACashInManager(context.get(MANAGER)).confirmClose(_commandId);
    }

    function failClose(uint256 _commandId) {

    }

}