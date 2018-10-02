pragma solidity 0.4.24;

import "../registry/Component.sol";

contract SessionOracle is Component {

    string constant COMPONENT_NAME = "session-oracle";

    event CloseSession(uint256 sessionId);

    constructor(address regAddr) Component(regAddr) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function closeSession(uint256 sessionId) external {
        emit CloseSession(sessionId);
    }

}