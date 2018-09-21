pragma solidity 0.4.24;

import "./ACashInController.sol";

contract CashInController is ACashInController {

    string constant COMPONENT_NAME = "cash-in-controller";

    constructor(address registry) ACashInController(registry) public {}

    function getName() internal pure returns (string name) {
        return COMPONENT_NAME;
    }

    function open(uint256 _sessionId) public onlyRegisteredApp returns (uint256) {
        return _cashChannelsManager().openCashInChannel(msg.sender, _sessionId);
    }

    function close(uint256 _sessionId, uint256 _channelId, uint256[] _fees, address[] _parties) public onlyRegisteredApp returns (bool) {
        _cashChannelsManager().closeCashInChannel(msg.sender, _sessionId, _channelId, _fees, _parties);
        return true;
    }

    function balanceOf(uint256 _channelId) public view onlyRegisteredApp returns (uint256) {
        return _cashChannelsManager().balanceOf(msg.sender, _channelId);
    }

}
