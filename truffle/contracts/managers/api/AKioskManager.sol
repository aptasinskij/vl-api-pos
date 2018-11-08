pragma solidity 0.4.24;

contract AKioskManager {

    // @formatter:off
    function getKiosk(
        address _application,
        uint256 _sessionId,
        function(
            uint256,
            string memory,
            string memory,
            string memory,
            string memory,
            uint256[] memory,
            uint256[] memory
        ) external _success,
        function(uint256) external _fail
    )
        external;
    // @formatter:on

    function confirmSuccessGetKioskInfo(
        uint256 _commandId,
        string _id,
        string _location,
        string _name,
        string _timezone,
        uint256[] _bills,
        uint256[] _amounts
    )
        public;

    function confirmFailGetKioskInfo(
        uint256 _commandId
    )
        public;

}
