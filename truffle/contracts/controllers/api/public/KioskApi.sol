pragma solidity 0.4.24;

interface KioskApi {

    /**
    @notice Get kiosk info
    @dev
    @param _sessionId: id of the session in which user wants to get info about kiosk
    @param _success: callback function(_id, _location, _name, _timezone, _bills, _amounts) for informing about successful case of getting kiosk info
    @param _fail: callback function(_id) for informing about failed case of getting kiosk info
    @return {
    }
    */
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
