pragma solidity 0.4.24;

import "./api/AKioskOracle.sol";
import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "../managers/api/AKioskManager.sol";

contract KioskOracle is AKioskOracle, Named("kiosk-oracle"), Mortal, Component {

    string constant MANAGER = "kiosk-manager";

    constructor(address _config) Component(_config) public {}

    // @formatter:off
    function onNextGetKioskInfo(
        uint256 _commandId,
        uint256 _sessionId
    )
        public
    // @formatter:on
    {
        emit GetKioskInfo(_commandId, _sessionId);
    }

    // @formatter:off
    function successGetKioskInfo(
        uint256 _commandId,
        string _id,
        string _location,
        string _name,
        string _timezone,
        uint256[] _bills,
        uint256[] _amounts
    )
        public
    // @formatter:on
    {
        AKioskManager(context.get(MANAGER)).confirmSuccessGetKioskInfo(
            _commandId,
            _id,
            _location,
            _name,
            _timezone,
            _bills,
            _amounts
        );
    }

    // @formatter:off
    function failGetKioskInfo(
        uint256 _commandId
    )
        public
    // @formatter:on
    {
        AKioskManager(context.get(MANAGER)).confirmFailGetKioskInfo(_commandId);
    }

}