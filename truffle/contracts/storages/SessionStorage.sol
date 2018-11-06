pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/ASessionStorage.sol";

contract SessionStorage is ASessionStorage, Named("session-storage"), Mortal, Component {
    
    using SessionLib for SessionLib.Session;

    mapping(uint256 => SessionLib.Session) private sessions;
    mapping(uint256 => SessionLib.Close) private sessionToClose;
    
    constructor(address _config) Component(_config) public {}

    // @formatter:on
    function createSession(
        uint256 _sessionId,
        uint256 _applicationId,
        string _kioskId,
        string _xToken
    )
        public
    {
        sessions[_sessionId] = SessionLib.Session({
            id: _sessionId,
            applicationId: _applicationId,
            kioskId: _kioskId,
            xToken: _xToken,
            status: SessionLib.Status.CREATING,
            hasActiveCashIn: false,
            hasActiveCashOut: false
        });
    }
    // @formatter:off

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
        )
    // @formatter:on
    {
        _applicationId = sessions[_sessionId].applicationId;
        _kioskId = sessions[_sessionId].kioskId;
        _xToken = sessions[_sessionId].xToken;
        _status = sessions[_sessionId].status;
        _hasActiveCashIn = sessions[_sessionId].hasActiveCashIn;
        _hasActiveCashOut = sessions[_sessionId].hasActiveCashOut;
    }

    // @formatter:off
    function retrieveSessionStatus(
        uint256 _sessionId
    )
        public
        view
        returns (
            SessionLib.Status _status
        )
    // @formatter:on
    {
        _status = sessions[_sessionId].status;
    }

    // @formatter:off
    function setSessionStatus(
        uint256 _sessionId,
        SessionLib.Status _status
    )
        public
    // @formatter:on
    {
        sessions[_sessionId].status = _status;
    }

    // @formatter:off
    function createSessionClose(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        public
    // @formatter:on
    {
        sessionToClose[_sessionId] = SessionLib.Close(_success, _fail);
    }

    // @formatter:off
    function retrieveSessionClose(
        uint256 _sessionId
    )
        public
        view
        returns (
            function(uint256) external _success,
            function(uint256) external _fail
        )
    // @formatter:on
    {
        _success = sessionToClose[_sessionId].success;
        _fail = sessionToClose[_sessionId].fail;
    }

}