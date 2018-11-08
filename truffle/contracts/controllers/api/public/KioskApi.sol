pragma solidity 0.4.24;

interface KioskApi {

    // @formatter:off
    function getKiosk(
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

}
