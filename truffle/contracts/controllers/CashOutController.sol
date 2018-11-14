pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/ACashOutController.sol";

contract CashOutController is ACashOutController, Named("cash-out-controller"), Mortal, Component {

    modifier onlyBy(string _name) {
        require(msg.sender == context.get(_name), "illegal access");
        _;
    }

    constructor(address _config) Component(_config) public {}

    function openCashOutChannel(
        uint256 _sessionId,
        uint256 _amount,
        uint256[] _bills,
        uint256[] _amounts,
        uint256[] _fees,
        address[] _parties,
        function(uint256) external _fail,
        function(uint256, uint256) external _success
    )
        external
        //TODO:implementation: check if msg.sender is registered application
    {
    }

    function respondOpened(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _callback
    )
        public
        onlyBy(MANAGER)
    {
        _callback(_sessionId, _cashInId);
    }

    function respondFailOpen(
        uint256 _sessionId,
        function(uint256, uint256) external _callback
    )
        public
        onlyBy(MANAGER)
    {
        _callback(_sessionId);
    }

    function closeCashOutChannel(
        uint256 _sessionId,
        uint256 _channelId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
)
        external
        //TODO:implementation: check if msg.sender is registered application
    {
    }

    function respondClosed(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _callback
    )
        public
        onlyBy(MANAGER)
    {
        _callback(_sessionId, _cashOutId);
    }

    function respondFailClose(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _callback
    )
        public
        onlyBy(MANAGER)
    {
        _callback(_sessionId, _cashOutId);
    }

}
