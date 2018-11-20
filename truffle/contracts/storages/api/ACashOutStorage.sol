pragma solidity 0.4.24;

import "../libs/CashOutLib.sol";

contract ACashOutStorage {

    // @formatter:off
    function createCashOut(
        address _application
    )
        public
        returns (
            uint256 _cashOutId
        );
    // @formatter:on

    // @formatter:off
    function createOpen(
        uint256 _cashOutId,
        string _requestId,
        string _kioskId,
        function(string memory, string memory) external _fail,
        function(string memory, string memory, uint256, uint256) external _success
    )
        public;
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
        public;
    // @formatter:on

    // @formatter:off
    function createValidate(
        uint256 _cashOutId,
        uint256 _sessionId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public;
    // @formatter:on

    // @formatter:off
    function createClose(
        uint256 _cashOutId,
        uint256 _sessionId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public;
    // @formatter:on

    // @formatter:off
    function createRollback(
        uint256 _cashOutId,
        function(uint256) external _fail,
        function(uint256) external _success
    )
        public;
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
        );
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
        );
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
        );
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
        );
    // @formatter:on

    function retrieveClose(
        uint256 _cashOutId
    )
        public
        view
        returns (
            uint256 _sessionId,
            function(uint256, uint256) external _fail,
            function(uint256, uint256) external _success
        );
    // @formatter:on

    function retrieveRollback(
        uint256 _cashOutId
    )
        public
        view
        returns (
            function(uint256) external _fail,
            function(uint256) external _success
        );
    // @formatter:on
}