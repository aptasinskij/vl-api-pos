pragma solidity 0.4.24;

contract ACameraStorage {

    // @formatter:off
    function saveStart(
        uint256 _sessionId,
        bool _enableLight,
        function(uint256, string memory, string memory, string memory) external _success,
        function(uint256, string memory) external _scanned,
        function(uint256) external _fail
    )
        public
        returns (
            uint256 _id
        );
    // @formatter:on

    // @formatter:off
    function retrieveStart(
        uint256 _id
    )
        public
        view
        returns (
            uint256 _sessionId,
            bool _enableLight,
            function(uint256, string memory, string memory, string memory) external _success,
            function(uint256, string memory) external _scanned,
            function(uint256) external _fail
        );
    // @formatter:on

    // @formatter:off
    function retrieveStartBySessionId(
        uint256 _id
    )
        public
        view
        returns (
            uint256 _sessionId,
            bool _enableLight,
            function(uint256, string memory, string memory, string memory) external _success,
            function(uint256, string memory) external _scanned,
            function(uint256) external _fail
        );
    // @formatter:on

    // @formatter:off
    function saveStop(
        uint256 _sessionId,
        function(uint256) external _success,
        function(uint256) external _fail
    )
        public
        returns (
            uint256 _id
        );
    // @formatter:on

    // @formatter:off
    function retrieveStop(
        uint256 _id
    )
        public
        view
        returns (
            uint256 _sessionId,
            function(uint256) external _success,
            function(uint256) external _fail
        );
    // @formatter:on
}
