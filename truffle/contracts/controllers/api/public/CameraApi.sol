pragma solidity 0.4.24;

interface CameraApi {

    /**
    @notice Scan QR code with lights
    @dev function to trigger QR code scanning with lights
    @param _sessionId - id of the session in which QR must occur
    @param _success - callback function(_commandId, _port, _url, _href) for informing about successful case of enabling camera
    @param _scanned - callback function(_sessionId, _qr) for informing about actual QR scanned data
    @param _fail - callback function(_commandId) for informing about failed case of enabling camera
    @return {
    }
    */
    // @formatter:off
    function scanQRCodeWithLights(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )
        external;
    // @formatter:on

    /**
    @notice Scan QR code without lights
    @dev function to trigger QR code scanning without lights
    @param _sessionId - id of the session in which QR must occur
    @param _success - callback function(_commandId, _port, _url, _href) for informing about successful case of enabling camera
    @param _scanned - callback function(_sessionId, _qr) for informing about actual QR scanned data
    @param _fail - callback function(_commandId) for informing about failed case of enabling camera
    @return {
    }
    */
    // @formatter:off
    function scanQRCodeWithoutLights(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )
        external;
    // @formatter:on

    /**
    @notice Stop QR code scanning
    @dev function to disable QR scanning
    @param _sessionId - id of the session in which camera must be disabled
    @param _success - callback function(_sessionId) to inform about camera successful disabling
    @param _fail - callback function(_sessionId) to inform about camera disabling fail
    @return {
    }
    */
    // @formatter:off
    function stopQRScanning(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        external;
    // @formatter:on

}
