pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/ACashInController.sol";
import {ApplicationLib} from "../libs/Libraries.sol";
import "../managers/api/ACashInManager.sol";

contract CashInController is ACashInController, Named("cash-in-controller"), Mortal, Component {

    using ApplicationLib for address;

    string constant MANAGER = "cash-channels-manager";

    modifier isRegistered {
        require(database.isRegistered(msg.sender), "only registered allowed");
        _;
    }

    modifier onlyManager {
        require(context.get(MANAGER) == msg.sender, "only manager allowed");
        _;
    }

    constructor(address _config) Component(_config) public {}

    function openCashInChannel(
        uint256 _sessionId,
        uint256 _maxBalance,
        function(uint256, uint256) external _success,
        function(uint256, uint256, uint256) external _update,
        function(uint256) external _fail
    )
        external
        isRegistered
        returns (bool _accepted)
    {
        _accepted = ACashInManager(context.get(MANAGER)).openCashInChannel(msg.sender, _sessionId, _maxBalance, _success, _update, _fail);
    }

    function respondOpened(uint256 _sessionId, uint256 _cashInId, function(uint256, uint256) external _callback) public onlyManager {
        _callback(_sessionId, _cashInId);
    }

    function respondFailOpen(uint256 _sessionId, function(uint256) external _callback) public onlyManager {
        _callback(_sessionId);
    }

    function respondUpdate(
        uint256 _sessionId,
        uint256 _cashInId,
        uint256 _amount,
        function(uint256, uint256, uint256) external _callback
    ) public
    {
        _callback(_sessionId, _cashInId, _amount);
    }

    function closeCashInChannel(
        uint256 _sessionId,
        uint256 _channelId,
        uint256[] _fees,
        address[] _parties,
        function(uint256, uint256) external _success,
        function(uint256, uint256) external _fail
    )
        external
        isRegistered
        returns (bool _accepted)
    {
        require(_fees.length == _parties.length, "split has not equals parameters");
        _accepted = ACashInManager(context.get(MANAGER)).closeCashInChannel(msg.sender, _sessionId, _channelId, _fees, _parties, _success, _fail);
    }

    function respondClosed(uint256 _sessionId, uint256 _cashInId, function(uint256, uint256) external _callback) public onlyManager {
        _callback(_sessionId, _cashInId);
    }

    function respondFailClose(uint256 _sessionId, uint256 _cashInId, function(uint256, uint256) external _callback) public onlyManager {
        _callback(_sessionId, _cashInId);
    }

    function balanceOf(uint256 _channelId) public view isRegistered returns (uint256) {
        return ACashInManager(context.get(MANAGER)).balanceOf(msg.sender, _channelId);
    }

}
