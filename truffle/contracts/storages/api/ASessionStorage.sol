pragma solidity 0.4.24;

import "../libs/SessionLib.sol";

contract ASessionStorage {

    event Saved(uint256 sessionId, uint256 appId, string xToken, uint256 status, string kioskId);
    event StatusUpdated(uint256 index, uint256 status);
    event ActiveCashIn(uint256 _sessionId, bool _flag);

    // @formatter:off
    function createSession(
        uint256 _sessionId,
        uint256 _applicationId,
        string _kioskId,
        string _xToken
    )
        public;
    // @formatter:on

    // @formatter:off
    function retrieveSession(
        uint256 _sessionId
    )
        public
        view
        returns (
            uint256 _applicationId,
            string _kioskId,
            string _xToken,
            SessionLib.Status _status,
            bool _hasActiveCashIn,
            bool _hasActiveCashOut
        );
    // @formatter:on

    // @formatter:off
    function retrieveSessionStatus(
        uint256 _sessionId
    )
        public
        view
        returns (
            SessionLib.Status _status
        );
    // @formatter:on

    // @formatter:off
    function setSessionStatus(
        uint256 _sessionId,
        SessionLib.Status _status
    )
        public;
    // @formatter:on

    // @formatter:off
    function createSessionClose(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        public;
    // @formatter:on

    // @formatter:off
    function retrieveSessionClose(
        uint256 _sessionId
    )
        public
        view
        returns (
            function(uint256) external _success,
            function(uint256) external _fail
        );
    // @formatter:on

}
