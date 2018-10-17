pragma solidity 0.4.24;

import {ApplicationLib} from "../libs/Libraries.sol";
import {ACashInController} from "./Controllers.sol";
import {ACashChannelsManager} from "../managers/Managers.sol";
import "../Platform.sol";

contract CashInController is ACashInController, Named("cash-in-controller"), Mortal, Component {

    using ApplicationLib for address;

    string constant MANAGER = "cash-channels-manager";

    modifier isRegistered {
        require(database.isRegistered(msg.sender), "only registered allowed");
        _;
    }

    constructor(address _config) Component(_config) public {}

    function open(uint256 _sessionId, uint256 _maxAmount) public isRegistered returns (uint256 _channelId) {
        _channelId = ACashChannelsManager(context.get(MANAGER)).openCashInChannel(msg.sender, _sessionId, _maxAmount);
    }

    function close(uint256 _sessionId, uint256 _channelId, uint256[] _fees, address[] _parties) public isRegistered returns (bool) {
        ACashChannelsManager(context.get(MANAGER)).closeCashInChannel(msg.sender, _sessionId, _channelId, _fees, _parties);
        return true;
    }

    function balanceOf(uint256 _channelId) public view isRegistered returns (uint256) {
        return ACashChannelsManager(context.get(MANAGER)).balanceOf(msg.sender, _channelId);
    }

}
