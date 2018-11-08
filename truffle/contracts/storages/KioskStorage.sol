pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "./api/AKioskStorage.sol";
import "../platform/Component.sol";
import "./libs/KioskLib.sol";

contract KioskStorage is AKioskStorage, Named("kiosk-storage"), Mortal, Component {

    using KioskLib for KioskLib.KioskInfoRequest;

    KioskLib.KioskInfoRequest[] private requests;

    constructor(address _config) Component(_config) public {}

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
        )
    // @formatter:on
    {
        return requests.push(KioskLib.KioskInfoRequest(_sessionId, _success, _fail)) - 1;
    }

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
        )
    // @formatter:on
    {
        _sessionId = requests[_id].sessionId;
        _success = requests[_id].success;
        _fail = requests[_id].fail;
    }
}