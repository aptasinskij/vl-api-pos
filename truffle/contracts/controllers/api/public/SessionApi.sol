pragma solidity 0.4.24;

interface SessionApi {

    // formatter:off
    function getKioskInfo(
        uint256 _sessionId
    )
        external
        view
        returns (
            string memory _id,
            string memory _location,
            string memory _name,
            string memory _timezone
        );
    // formatter:on

    // formatter:off
    function closeSession(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        external;
    // formatter:on

}
