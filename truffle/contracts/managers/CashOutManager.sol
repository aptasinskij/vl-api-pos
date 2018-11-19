pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "./api/ACashOutManager.sol";
import "../platform/Component.sol";
import "../oracles/api/ACashOutOracle.sol";
import "../storages/api/ACashOutStorage.sol";
import "../controllers/api/ACashOutController.sol";

contract CashOutManager is ACashOutManager, Named("cash-out-manager"), Mortal, Component {

    string constant CONTROLLER = "cash-out-controller";
    string constant STORAGE = "cash-out-storage";
    string constant ORACLE = "cash-out-oracle";

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
    {
        //TODO:implementation: verify msg.sender is CashOutController
        ACashOutStorage cashOutStorage = ACashOutStorage(context.get(STORAGE));
        uint256 cashOutId = cashOutStorage.createCashOut(_application);
        cashOutStorage.createOpen(cashOutId, _kioskId, _fail, _success);
        cashOutStorage.createAccount(cashOutId, _toWithdraw, 100, 5000, _fees, _parties);
        ACashOutOracle(context.get(ORACLE)).onNextOpenCashOut(cashOutId);
    }

    function confirmOpen(
        uint256 _commandId
    )
        public
    {
        (string memory kioskId,,function(string memory, uint256) external success) = ACashOutStorage(context.get(STORAGE))
        .retrieveOpen(_commandId);
        ACashOutController(context.get(CONTROLLER)).respondOpened(kioskId, _commandId, success);
    }

    function confirmFailOpen(
        uint256 _commandId
    )
        public
    {
        (string memory kioskId,function(string memory) external fail,) = ACashOutStorage(context.get(STORAGE))
        .retrieveOpen(_commandId);
        ACashOutController(context.get(CONTROLLER)).respondFailOpen(kioskId, fail);
    }

    function validateCashOutChannel(
        address _application,
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public
    {
        //TODO:implementation: verify msg.sender is CashOutController
        //TODO:implementation: verify _application the owner of CashOut
        ACashOutStorage cashOutStorage = ACashOutStorage(context.get(STORAGE));
        cashOutStorage.createValidate(_cashOutId, _sessionId, _fail, _success);
        (uint256 toWithdraw,,,,) = cashOutStorage.retrieveAccount(_cashOutId);
        uint256[] memory bills = new uint256[](2);
        bills[0] = 5;
        bills[1] = 20;
        ACashOutOracle(context.get(ORACLE)).onNextValidateCashOut(_cashOutId, _sessionId, toWithdraw, bills);
    }

    function confirmValidate(
        uint256 _commandId
    )
        public
    {
        (uint256 sessionId,,function(uint256, uint256) external success) = ACashOutStorage(context.get(STORAGE)).retrieveValidate(_commandId);
        ACashOutController(context.get(CONTROLLER)).respondValidated(sessionId, _commandId, success);
    }

    function confirmFailValidate(
        uint256 _commandId
    )
        public
    {
        (uint256 sessionId,function(uint256, uint256) external fail,) = ACashOutStorage(context.get(STORAGE)).retrieveValidate(_commandId);
        ACashOutController(context.get(CONTROLLER)).respondValidated(sessionId, _commandId, fail);
    }

    function closeCashOutChannel(
        address _application,
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public
    {
        //TODO:implementation: verify msg.sender is CashOutController
        //TODO:implementation: verify _application the owner of CashOut
        ACashOutStorage cashOutStorage = ACashOutStorage(context.get(STORAGE));
        cashOutStorage.createClose(_cashOutId, _sessionId, _fail, _success);
        (uint256 toWithdraw,,,,) = cashOutStorage.retrieveAccount(_cashOutId);
        uint256[] memory bills = new uint256[](2);
        bills[0] = 5;
        bills[1] = 20;
        ACashOutOracle(context.get(ORACLE)).onNextCloseCashOut(_cashOutId, _sessionId, toWithdraw, bills);
    }

    function confirmClose(
        uint256 _commandId
    )
        public
    {
        (uint256 sessionId,,function(uint256, uint256) external success) = ACashOutStorage(context.get(STORAGE)).retrieveClose(_commandId);
        ACashOutController(context.get(CONTROLLER)).respondClosed(sessionId, _commandId, success);
    }

    function confirmFailClose(
        uint256 _commandId
    )
        public
    {
        (uint256 sessionId,function(uint256, uint256) external fail,) = ACashOutStorage(context.get(STORAGE)).retrieveClose(_commandId);
        ACashOutController(context.get(CONTROLLER)).respondClosed(sessionId, _commandId, fail);
    }

    function rollbackCashOutChannel(
        address _application,
        uint256 _cashOutId,
        function(uint256) external _fail,
        function(uint256) external _success
    )
        public
    {
        //TODO:implementation: verify msg.sender is CashOutController
        //TODO:implementation: verify _application the owner of CashOut
        ACashOutStorage(context.get(STORAGE)).createRollback(_cashOutId, _fail, _success);
        ACashOutOracle(context.get(ORACLE)).onNextRollbackCashOut(_cashOutId);
    }

    function confirmRollback(
        uint256 _commandId
    )
        public
    {
        (,function(uint256) external success) = ACashOutStorage(context.get(STORAGE)).retrieveRollback(_commandId);
        ACashOutController(context.get(CONTROLLER)).respondRolledBack(_commandId, success);
    }

    function confirmFailRollback(
        uint256 _commandId
    )
        public
    {
        (function(uint256) external fail,) = ACashOutStorage(context.get(STORAGE)).retrieveRollback(_commandId);
        ACashOutController(context.get(CONTROLLER)).respondRolledBack(_commandId, fail);
    }

}