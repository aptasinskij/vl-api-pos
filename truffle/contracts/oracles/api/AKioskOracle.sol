pragma solidity 0.4.24;

contract AKioskOracle {

    event GetKioskInfo(uint256 _commandId, uint256 _sessionId);

    // @formatter:off
    function onNextGetKioskInfo(
        uint256 _commandId,
        uint256 _sessionId
    )
        public;
    // @formatter:on

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
        public;
    // @formatter:on

    // @formatter:off
    function failGetKioskInfo(
        uint256 _commandId
    )
        public;
    // @formatter:on

}
