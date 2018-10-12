pragma solidity 0.4.24;

import {ACameraManager} from "./Managers.sol";
import "../registry/Component.sol";

contract CameraManager is ACameraManager, Component {

    string constant COMPONENT_NAME = "camera-manager";

    constructor(address registry) Component(registry) public {}

    function getName() internal pure returns (string name) {
        return COMPONENT_NAME;
    }

    function scanQRCodeWithLights(
        address application,
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _scanned,
        function(uint256) external _success,
        function(uint256) external _fail
    )   // @formatter:off
        public
        returns (bool _accepted)
        // @formatter:on
    {

    }

    function scanQRCode(
        address application,
        uint256 _sessionId,
        function(uint256, string memory, string memory, string memory) external _scanned,
        function(uint256) external _success,
        function(uint256) external _fail
    )   // @formatter:off
        public
        returns (bool _accepted)
        // @formatter:on
    {

    }

    function stopQRScanning(
        address application,
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )   // @formatter:off
        public
        returns (bool _accepted)
        // @formatter:on
    {

    }

}
