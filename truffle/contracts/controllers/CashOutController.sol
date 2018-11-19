pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/ACashOutController.sol";
import "../managers/api/ACashOutManager.sol";

contract CashOutController is ACashOutController, Named("cash-out-controller"), Mortal, Component {

    string constant MANAGER = "cash-out-manager";

    constructor(address _config) Component(_config) public {}

    // @formatter:off
    function openCashOutChannel(
        string _kioskId,
        uint256 _toWithdraw,
        uint256[] _fees,
        address[] _parties,
        function(string memory) external _fail,
        function(string memory, uint256) external _success
    )
        external
    // @formatter:on
    {
        //TODO:implementation: check that msg.sender is registered application, validate parameters
        ACashOutManager(context.get(MANAGER)).openCashOutChannel(
            msg.sender,
            _kioskId,
            _toWithdraw,
            _fees,
            _parties,
            _fail,
            _success
        );
    }

    // @formatter:off
    function validateCashOutChannel(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        external
    // @formatter:on
    {
        //TODO:implementation: check that msg.sender is registered application, validate parameters
        ACashOutManager(context.get(MANAGER)).validateCashOutChannel(
            msg.sender,
            _sessionId,
            _cashOutId,
            _fail,
            _success
        );
    }

    // @formatter:off
    function closeCashOutChannel(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        external
    // @formatter:on
    {
        //TODO:implementation: check that msg.sender is registered application, validate parameters
        ACashOutManager(context.get(MANAGER)).closeCashOutChannel(
            msg.sender,
            _sessionId,
            _cashOutId,
            _fail,
            _success
        );
    }

    // @formatter:off
    function rollbackCashOutChannel(
        uint256 _cashOutId,
        function(uint256) external _fail,
        function(uint256) external _success
    )
        external
    // @formatter:on
    {
        //TODO:implementation: check that msg.sender is registered application, validate parameters
        ACashOutManager(context.get(MANAGER)).rollbackCashOutChannel(
            msg.sender,
            _cashOutId,
            _fail,
            _success
        );
    }

    // @formatter:off
    function respondOpened(
        string _kioskId,
        uint256 _cashOutId,
        function(string memory, uint256) external _callback
    )
        public
    // @formatter:on
    {
        _callback(_kioskId, _cashOutId);
    }

    // @formatter:off
    function respondFailOpen(
        string _kioskId,
        function(string memory) external _callback
    )
        public
    // @formatter:on
    {
        _callback(_kioskId);
    }

    // @formatter:off
    function respondValidated(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _callback
    )
        public
    // @formatter:on
    {
        _callback(_sessionId, _cashOutId);
    }

    // @formatter:off
    function respondClosed(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _callback
    )
        public
    // @formatter:on
    {
        _callback(_sessionId, _cashOutId);
    }

    // @formatter:off
    function respondRolledBack(
        uint256 _cashOutId,
        function(uint256) external _callback
    )
        public
    // @formatter:on
    {
        _callback(_cashOutId);
    }

}
