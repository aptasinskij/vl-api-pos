pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "./api/ACashOutManager.sol";
import "../platform/Component.sol";
import "../controllers/api/ACashOutController.sol";

contract CashOutManager is ACashOutManager, Named("cash-out-manager"), Mortal, Component {

    string constant CONTROLLER = "cash-out-controller";

    modifier onlyBy(string _name) {
        require(context.get(_name) == msg.sender, "access not allowed");
        _;
    }

    constructor(address _config) Component(_config) public {}

    function openCashOutChannel(
        address _application,
        string _kioskId,
        uint256 _toWithdraw,
        uint256[] _fees,
        address[] _parties,
        function(string memory) external _fail,
        function(string memory, uint256) external _success
    )
        public
        onlyBy(CONTROLLER)
    {
    }

    function validateCashOutChannel(
        address _application,
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public
        onlyBy(CONTROLLER)
    {
    }

    function closeCashOutChannel(
        address _application,
        uint256 _sessionId,
        uint256 _channelId,
        uint256[] _fees,
        address[] _parties,
        function(uint256, uint256) external _success,
        function(uint256, uint256) external _fail
    )
        public
        onlyBy(CONTROLLER)
    {
    }

    function rollbackCashOutChannel(
        address _application,
        uint256 _cashOutId,
        function(uint256) external _fail,
        function(uint256) external _success
    )
        public
        onlyBy(CONTROLLER)
    {
    }

}