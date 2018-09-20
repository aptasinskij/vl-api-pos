pragma solidity 0.4.24;

import "./ACashInController.sol";
import "../services/ICashChannelsManager.sol";

contract CashInController is ACashInController {

    string constant COMPONENT_NAME = "cash-in-controller";
    string constant CASH_CHANNELS_MANAGER = "cash-channels-manager";

    constructor(address registry) ACashInController(registry) public {}

    function getName() internal pure returns (string name) {
        return COMPONENT_NAME;
    }

    function open(uint256 _sessionId) public onlyRegisteredApp returns (uint256) {
        return ICashChannelsManager(lookup(CASH_CHANNELS_MANAGER)).openCashInChannel(msg.sender, _sessionId);
    }

    function close(uint256 _sessionId, uint256 _channelId, uint256[] _fees, address[] _parties) public onlyRegisteredApp returns (bool) {
        ICashChannelsManager(lookup(CASH_CHANNELS_MANAGER)).closeCashInChannel(msg.sender, _sessionId, _channelId, _fees, _parties);
        return true;
    }

    function balanceOf(uint256 _channelId) public view returns (uint256);

}
