pragma solidity 0.4.24;

import "./AKioskController.sol";
import "../../platform/Named.sol";
import "../../platform/Mortal.sol";
import "../../platform/Component.sol";
import "../../managers/api/AKioskManager.sol";

contract KioskController is AKioskController, Named("kiosk-controller"), Mortal, Component {

    string constant MANAGER = "kiosk-manager";

    constructor(address _config) Component(_config) public {}

    // @formatter:off
    function getKioskInfo(
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
        external
    // @formatter:on
    {
        AKioskManager(context.get(MANAGER)).getKioskInfo(msg.sender, _sessionId, _success, _fail);
    }

}
