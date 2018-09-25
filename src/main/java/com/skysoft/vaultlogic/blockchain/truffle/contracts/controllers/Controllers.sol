pragma solidity 0.4.24;

import "../registry/Component.sol";

contract AbstractController is Component {

    modifier onlyRegisteredApp {
        require(_applicationStorage().isRegistered(msg.sender), "Illegal access");
        _;
    }

    constructor(address registry) Component(registry) internal {}

}

contract ACashInController is AbstractController {

    constructor(address registry) AbstractController(registry) internal {}

    function open(uint256 _sessionId) public returns(uint256);

    function close(uint256 _sessionId, uint256 _channelId, uint256[] _fees, address[] _parties) public returns(bool);

    function balanceOf(uint256 _channelId) public view returns(uint256);

}
