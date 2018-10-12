pragma solidity 0.4.24;

import {ASessionOracle} from "./Oracles.sol";

contract SessionOracle is ASessionOracle {

    string constant COMPONENT_NAME = "session-oracle";

    event CloseSession(uint256 sessionId);

    constructor(address regAddr) ASessionOracle(regAddr) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function closeSession(uint256 sessionId) public {
        emit CloseSession(sessionId);
    }

}