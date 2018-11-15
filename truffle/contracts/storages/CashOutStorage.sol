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

    constructor(address _config) Component(_config) public {}

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

    function createOpen(
        uint256 _cashOutId,
        string _kioskId,
        function(string memory) external _fail,
        function(string memory, uint256) external _success
    )
        public
    {
        channelOpen[_cashOutId] = CashOutLib.Open(_kioskId, _fail, _success);
    }

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

}