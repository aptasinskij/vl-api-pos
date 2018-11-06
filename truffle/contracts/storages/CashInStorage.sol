pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Component.sol";
import "../platform/Mortal.sol";
import "./api/ACashInStorage.sol";

contract CashInStorage is ACashInStorage, Named("cash-in-storage"), Mortal, Component {

    using CashInLib for *;

    CashInLib.CashIn[] private channels;

    mapping(uint256 => CashInLib.Split) private channelSplit;

    mapping(uint256 => CashInLib.Account) private channelAccount;

    mapping(uint256 => CashInLib.Close) private channelClose;

    mapping(uint256 => CashInLib.Open) private channelOpen;

    constructor(address _config) Component(_config) public {}

    function createCashIn(
        uint256 _sessionId,
        address _application
    )
        public
        returns (
            uint256 _id
        )
    {
        _id = channels.push(CashInLib.CashIn(_sessionId, _application, CashInLib.Status.CREATING)) - 1;
    }

    function retrieveCashIn(
        uint256 _id
    )
        public
        view
        returns (
            uint256 _sessionId,
            address _application,
            CashInLib.Status _status
        )
    {
        _sessionId = channels[_id].sessionId;
        _application = channels[_id].application;
        _status = channels[_id].status;
    }

    function retrieveCashInSessionId(
        uint256 _id
    )
        public
        view
        returns (
            uint256 _sessionId
        )
    {
        _sessionId = channels[_id].sessionId;
    }

    function retrieveCashInApplication(
        uint256 _id
    )
        public
        view
        returns (
            address _application
        )
    {
        _application = channels[_id].application;
    }

    function setCashInStatus(
        uint256 _cashInId,
        CashInLib.Status _status
    )
        public
    {
        channels[_cashInId].status = _status;
    }

    function retrieveCashInStatus(
        uint256 _cashInId
    )
        public
        view
        returns (
            CashInLib.Status _status
        )
    {
        _status = channels[_cashInId].status;
    }

    function createSplit(
        uint256 _cashInId,
        uint256[] _fees,
        address[] _parties
    )
        public
    {
        channelSplit[_cashInId] = CashInLib.Split(_fees, _parties);
    }

    function retrieveSplit(
        uint256 _cashInId
    )
        public
        view
        returns (
            uint256[] _fees,
            address[] _parties
        )
    {
        _fees = channelSplit[_cashInId].fees;
        _parties = channelSplit[_cashInId].parties;
    }

    function createAccount(
        uint256 _cashInId,
        uint256 _maxBalance,
        uint256 _fee
    )
        public
    {
        channelAccount[_cashInId] = CashInLib.Account(0, _maxBalance, _fee);
    }

    function retrieveAccount(
        uint256 _cashInId
    )
        public
        view
        returns (
            uint256 _balance,
            uint256 _maxBalance,
            uint256 _fee
    )
    {
        _balance = channelAccount[_cashInId].balance;
        _maxBalance = channelAccount[_cashInId].maxBalance;
        _fee = channelAccount[_cashInId].fee;
    }

    function setAccountBalance(
        uint256 _cashInId,
        uint256 _amount
    )
        public
    {
        channelAccount[_cashInId].balance = _amount;
    }

    function createClose(
        uint256 _cashInId,
        uint256 _sessionId,
        function(uint256, uint256) external _success,
        function(uint256, uint256) external _fail
    )
        public
    {
        channelClose[_cashInId] = CashInLib.Close(_sessionId, _success, _fail);
    }

    function retrieveClose(
        uint256 _cashInId
    )
        public
        view
        returns (
            uint256 _sessionId,
            function(uint256, uint256) external _success,
            function(uint256, uint256) external _fail
    )
    {
        _sessionId = channelClose[_cashInId].sessionId;
        _success = channelClose[_cashInId].success;
        _fail = channelClose[_cashInId].fail;
    }

    function createOpen(
        uint256 _cashInId,
        uint256 _sessionId,
        uint256 _maxBalance,
        function(uint256) external _fail,
        function(uint256, uint256) external _success,
        function(uint256, uint256, uint256) external _update
    )
        public
    {
        channelOpen[_cashInId] = CashInLib.Open(_sessionId, _maxBalance, _fail, _success, _update);
    }

    function retrieveOpen(
        uint256 _cashInId
    )
        public
        view
        returns (
            uint256 _sessionId,
            uint256 _maxBalance,
            function(uint256) external _fail,
            function(uint256, uint256) external _success,
            function(uint256, uint256, uint256) external _update
    )
    {
        _sessionId = channelOpen[_cashInId].sessionId;
        _maxBalance = channelOpen[_cashInId].maxBalance;
        _fail = channelOpen[_cashInId].fail;
        _success = channelOpen[_cashInId].success;
        _update = channelOpen[_cashInId].update;
    }

}