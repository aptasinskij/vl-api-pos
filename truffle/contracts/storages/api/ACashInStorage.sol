pragma solidity 0.4.24;

import "../libs/CashInLib.sol";

contract ACashInStorage {

    function createCashIn(
        uint256 _sessionId,
        address _application
    )
        public
        returns (
            uint256 _id
        );

    function retrieveCashIn(
        uint256 _id
    )
        public
        view
        returns (
            uint256 _sessionId,
            address _application,
            CashInLib.Status _status
        );

    function retrieveCashInSessionId(
        uint256 _id
    )
        public
        view
        returns (
            uint256 _sessionId
        );

    function retrieveCashInApplication(
        uint256 _id
    )
        public
        view
        returns (
            address _application
        );

    function setCashInStatus(
        uint256 _cashInId,
        CashInLib.Status _status
    )
        public;

    function retrieveCashInStatus(
        uint256 _cashInId
    )
        public
        view
        returns (
            CashInLib.Status _status
        );

    function createSplit(
        uint256 _cashInId,
        uint256[] _fees,
        address[] _parties
    )
        public;

    function retrieveSplit(
        uint256 _cashInId
    )
        public
        view
        returns (
            uint256[] _fees,
            address[] _parties
        );

    function createAccount(
        uint256 _cashInId,
        uint256 _maxBalance,
        uint256 _fee
    )
        public;

    function retrieveAccount(
        uint256 _cashInId
    )
        public
        view
        returns (
            uint256 _balance,
            uint256 _maxBalance,
            uint256 _fee
        );

    function setAccountBalance(
        uint256 _cashInId,
        uint256 _amount
    )
        public;

    function createClose(
        uint256 _cashInId,
        uint256 _sessionId,
        function(uint256, uint256) external _success,
        function(uint256, uint256) external _fail
    )
        public;

    function retrieveClose(
        uint256 _cashInId
    )
        public
        view
        returns (
            uint256 _sessionId,
            function(uint256, uint256) external _success,
            function(uint256, uint256) external _fail
        );

    function createOpen(
        uint256 _cashInId,
        uint256 _sessionId,
        uint256 _maxBalance,
        function(uint256) external _fail,
        function(uint256, uint256) external _success,
        function(uint256, uint256, uint256) external _update
    )
        public;

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
        );

}
