pragma solidity 0.4.24;

library CameraLib {

    struct Start {
        uint256 sessionId;
        bool enableLight;
        function(uint256, string memory, string memory, string memory) external success;
        function(uint256, string memory) external scanned;
        function(uint256) external fail;
    }

    struct Stop {
        uint256 sessionId;
        function(uint256) external success;
        function(uint256) external fail;
    }

}
