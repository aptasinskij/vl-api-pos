pragma solidity 0.4.24;

import "./AbstractController.sol";

contract ACashInController is AbstractController {

    constructor(address registry) AbstractController(registry) public {}

    function open(uint256 _sessionId) public returns(uint256);

    function close(uint256 _sessionId, uint256 _channelId, uint256[] _fees, address[] _parties) public returns(bool);

    function balanceOf(uint256 _channelId) public view returns(uint256);

}
