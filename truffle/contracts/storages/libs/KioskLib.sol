pragma solidity 0.4.24;

library  KioskLib {

    struct KioskInfoRequest {
        uint256 sessionId;
        function(
            uint256,
            string memory,
            string memory,
            string memory,
            string memory,
            uint256[] memory,
            uint256[] memory
        ) external success;
        function(uint256) external fail;
    }

}
