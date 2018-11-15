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
        string _kioskId,
        uint256 _toWithdraw,
        uint256[] _fees,
        address[] _parties,
        function(string memory) external _fail,
        function(string memory, uint256) external _success
    )
        external
        //TODO:implementation: check if msg.sender is registered application
    {
    }

    function respondOpened(
        string _kioskId,
        uint256 _cashOutId,
        function(string memory, uint256) external _callback
    )
        public
    {
        _callback(_kioskId, _cashOutId);
    }

    function respondFailOpen(
        string _kioskId,
        function(string memory) external _callback
    )
        public
    {
        _callback(_kioskId);
    }

    function validateCashOutChannel(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        external
        //TODO:implementation: check if msg.sender is registered application
    {
    }

    function respondValidated(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _callback
    )
        public
    {
        _callback(_sessionId, _cashOutId);
    }

    function closeCashOutChannel(
        uint256 _sessionId,
        uint256 _cashOutId,
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
    {
        _callback(_sessionId, _cashOutId);
    }

    function rollbackCashOutChannel(
        uint256 _cashOutId,
        function(uint256) external _fail,
        function(uint256) external _success
    )
        external
        //TODO:implementation: check if msg.sender is registered application
    {
    }

    function respondRolledBack(
        uint256 _cashOutId,
        function(uint256) external _callback
    )
        public
    {
        _callback(_cashOutId);
    }

}
