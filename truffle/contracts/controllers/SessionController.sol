pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "./api/ASessionController.sol";
import {KioskLib, SessionLib, ApplicationLib} from "../libs/Libraries.sol";


contract SessionController is ASessionController, Named("session-controller"), Mortal, Component {

    using SessionLib for address;
    using ApplicationLib for address;

    modifier isRegistered {
        require(database.isRegistered(msg.sender), "only registered allowed");
        _;
    }

    constructor(address _config) Component(_config) public {}

    function getKioskInfo(
        uint256 _sessionId
    ) 
        public 
        isRegistered 
        view 
        returns (
            string memory _id, 
            string memory _location, 
            string memory _name, 
            string memory _timezone
        ) 
    {
        KioskLib.Kiosk memory kiosk = database.retrieveSessionKiosk(_sessionId);
        return (kiosk.id, kiosk.location, kiosk.name, kiosk.timezone);
    }

    // formatter:off
    function closeSession(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        external
        returns (
            bool _accepted
        )
    {
        _accepted = true;
    }
    // formatter:on

    // formatter:off
    function respondClose(
        uint256 _sessionId,
        function(uint256) external _callback
    )
        public
    // formatter:on
    {

    }

    // formatter:off
    function respondFailClose(
        uint256 _sessionId,
        function(uint256) external _callback
    )
        public
    // formatter:on
    {

    }

}
