pragma solidity 0.4.24;

import {ACameraController} from "./Controllers.sol";

contract CameraController is ACameraController {

    string constant COMPONENT_NAME = "camera-controller";

    constructor(address registry) ACameraController(registry) public {}

    function getName() internal pure returns (string name) {
        return COMPONENT_NAME;
    }

    function scanQRCodeWithLights(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _scanned,
        function(uint256) external _success,
        function(uint256) external _fail
    )   // @formatter:off
        public
        onlyRegisteredApp
        returns (bool _accepted)
        // @formatter:on
    {

    }

    function scanQRCode(
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _scanned,
        function(uint256) external _success,
        function(uint256) external _fail
    )   // @formatter:off
        public
        onlyRegisteredApp
        returns (bool _accepted)
        // @formatter:on
    {

    }

    function stopQRScanning(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )   // @formatter:off
        public
        onlyRegisteredApp
        returns (bool _accepted)
        // @formatter:on
    {

    }

}
