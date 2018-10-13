pragma solidity 0.4.24;

import {ASessionOracle} from "./Oracles.sol";
import {Named} from "../Platform.sol";

contract SessionOracle is ASessionOracle, Named("session-oracle") {

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