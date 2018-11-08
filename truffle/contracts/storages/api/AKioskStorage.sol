pragma solidity 0.4.24;

contract AKioskStorage {

    // @formatter:off
    function createKioskInfoRequest(
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
        public
        returns (
            uint256 _id
        );
    // @formatter:on

    // @formatter:off
    function retrieveKioskInfoRequest(
        uint256 _id
    )
        public
        view
        returns (
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
        );
    // @formatter:on

}
