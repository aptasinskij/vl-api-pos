pragma solidity 0.4.24;

import "../libs/CashOutLib.sol";

contract ACashOutStorage {

    function createCashOut(
        uint256 _sessionId,
        address _application
    )
        public
        returns (
            uint256 _id
        );

    function retrieveCashOut(
        uint256 _id
    )
        public
        view
        returns (
            uint256 _sessionId,
            address _application,
            CashOutLib.Status _status
        );

    //TODO: createAccount and retrieveAccount methods

    function createSplit(
        uint256 _cashOutId,
        uint256[] _fees,
        address[] _parties
    )
        public;

    function retrieveSplit(
        uint256 _cashOutId
    )
        public
        view
        returns (
            uint256[] _fees,
            address[] _parties
        );

    function createCassette(
        uint256 _cashOutId,
        uint256[] _bills,
        uint256[] _amounts
    )
        public;

    function retrieveCassette(
        uint256 _cashOutId
    )
        public
        view
        returns (
            uint256[] _bills,
            uint256[] _amounts
        );

    function createOpen(
        uint256 _cashOutId,
        uint256 _sessionId,
        uint256 _amount,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public;

    function retrieveOpen(
        uint256 _cashOutId
    )
        public
        view
        returns (
            uint256 _sessionId,
            uint256 _amount,
            function(uint256, uint256) external _fail,
            function(uint256, uint256) external _success
        );

    function createClose(
        uint256 _cashOutId,
        uint256 _sessionId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public;

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

}
