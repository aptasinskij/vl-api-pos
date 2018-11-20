pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Component.sol";
import "../platform/Mortal.sol";
import "./api/ACashOutStorage.sol";

contract CashOutStorage is ACashOutStorage, Named("cash-out-storage"), Mortal, Component {

    using CashOutLib for *;

    CashOutLib.CashOut[] private channels;
    mapping(uint256 => CashOutLib.Open) private channelOpen;
    mapping(uint256 => CashOutLib.Account) private channelAccount;
    mapping(uint256 => CashOutLib.Validate) private channelValidate;
    mapping(uint256 => CashOutLib.Close) private channelClose;
    mapping(uint256 => CashOutLib.Rollback) private channelRollback;

    constructor(address _config) Component(_config) public {}

    // @formatter:off
    function createCashOut(
        address _application
    )
        public
        returns (
            uint256 _cashOutId
        )
    {
        _cashOutId = channels.push(CashOutLib.CashOut(_application, CashOutLib.Status.CREATING)) - 1;
    }
    // @formatter:on

    // @formatter:off
    function createOpen(
        uint256 _cashOutId,
        string _requestId,
        string _kioskId,
        function(string memory, string memory) external _fail,
        function(string memory, string memory, uint256, uint256) external _success
    )
        public
    {
        channelOpen[_cashOutId] = CashOutLib.Open(_requestId, _kioskId, _fail, _success);
    }
    // @formatter:on

    // @formatter:off
    function createAccount(
        uint256 _cashOutId,
        uint256 _toWithdraw,
        uint256 _VLFee,
        uint256 _reserve,
        uint256[] _fees,
        address[] _parties
    )
        public
    {
        channelAccount[_cashOutId] = CashOutLib.Account(_toWithdraw, _VLFee, _reserve, _fees, _parties);
    }
    // @formatter:on

    // @formatter:off
    function createValidate(
        uint256 _cashOutId,
        uint256 _sessionId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public
    {
        channelValidate[_cashOutId] = CashOutLib.Validate(_sessionId, _fail, _success);
    }
    // @formatter:on

    // @formatter:off
    function createClose(
        uint256 _cashOutId,
        uint256 _sessionId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public
    {
        channelClose[_cashOutId] = CashOutLib.Close(_sessionId, _fail, _success);
    }
    // @formatter:on

    // @formatter:off
    function createRollback(
        uint256 _cashOutId,
        function(uint256) external _fail,
        function(uint256) external _success
    )
        public
    {
        channelRollback[_cashOutId] = CashOutLib.Rollback(_fail, _success);
    }
    // @formatter:on

    // @formatter:off
    function retrieveCashOut(
        uint256 _cashOutId
    )
        public
        view
        returns (
            address _application,
            CashOutLib.Status _status
        )
    {
        _application = channels[_cashOutId].application;
        _status = channels[_cashOutId].status;
    }
    // @formatter:on

    // @formatter:off
    function retrieveAccount(
        uint256 _cashOutId
    )
        public
        view
        returns (
            uint256 _toWithdraw,
            uint256 _VLFee,
            uint256 _reserve,
            uint256[] _fees,
            address[] _parties
        )
    {
        _toWithdraw = channelAccount[_cashOutId].toWithdraw;
        _VLFee = channelAccount[_cashOutId].VLFee;
        _reserve = channelAccount[_cashOutId].reserve;
        _fees = channelAccount[_cashOutId].fees;
        _parties = channelAccount[_cashOutId].parties;
    }
    // @formatter:on

    // @formatter:off
    function retrieveOpen(
        uint256 _cashOutId
    )
        public
        view
        returns (
            string memory _requestId,
            string memory _kioskId,
            function(string memory, string memory) external _fail,
            function(string memory, string memory, uint256, uint256) external _success
        )
    {
        _requestId = channelOpen[_cashOutId].requestId;
        _kioskId = channelOpen[_cashOutId].kioskId;
        _fail = channelOpen[_cashOutId].fail;
        _success = channelOpen[_cashOutId].success;
    }
    // @formatter:on

    // @formatter:off
    function retrieveValidate(
        uint256 _cashOutId
    )
        public
        view
        returns (
            uint256 _sessionId,
            function(uint256, uint256) external _fail,
            function(uint256, uint256) external _success
        )
    {
        _sessionId = channelValidate[_cashOutId].sessionId;
        _fail = channelValidate[_cashOutId].fail;
        _success = channelValidate[_cashOutId].success;
    }
    // @formatter:on

    // @formatter:off
    function retrieveClose(
        uint256 _cashOutId
    )
        public
        view
        returns (
            uint256 _sessionId,
            function(uint256, uint256) external _fail,
            function(uint256, uint256) external _success
        )
    {
        _sessionId = channelClose[_cashOutId].sessionId;
        _fail = channelClose[_cashOutId].fail;
        _success = channelClose[_cashOutId].success;
    }
    // @formatter:on

    // @formatter:off
    function retrieveRollback(
        uint256 _cashOutId
    )
        public
        view
        returns (
            function(uint256) external _fail,
            function(uint256) external _success
        )
    {
        _fail = channelRollback[_cashOutId].fail;
        _success = channelRollback[_cashOutId].success;
    }
    // @formatter:on

}