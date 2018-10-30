pragma solidity 0.4.24;

interface CameraApi {

    ///@dev function to trigger QR code scanning with lights
    ///@param _sessionId - id of the session in which QR must occur
    ///@param _success - callback function for informing about successful case of enabling camera
    ///@param _scanned - callback function for informing about actual QR scanned data
    ///@param _fail - callback function for informing about failed case of enabling camera
    // @formatter:off
    function scanQRCodeWithLights(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )
        external
        returns (
            bool _accepted
        );
    // @formatter:on

    ///@dev function to trigger QR code scanning without lights
    ///@param _sessionId - id of the session in which QR must occur
    ///@param _success - callback function for informing about successful case of enabling camera
    ///@param _scanned - callback function for informing about actual QR scanned data
    ///@param _fail - callback function for informing about failed case of enabling camera
    // @formatter:off
    function scanQRCode(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )
        external
        returns (
            bool _accepted
        );
    // @formatter:on

    ///@dev function to disable QR scanning
    ///@param _sessionId - id of the session in which camera must be disabled
    ///@param _success - callback function to inform about camera successful disabling
    ///@param _fail - callback function to inform about camera disabling fail
    // @formatter:off
    function stopQRScanning(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        external
        returns (
            bool _accepted
        );
    // @formatter:on

}
