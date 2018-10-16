pragma solidity 0.4.24;

import {ASessionOracle} from "./Oracles.sol";
import "../Platform.sol";

contract SessionOracle is ASessionOracle, Named("session-oracle"), Mortal, Component  {

    constructor(address _config) Component(_config) public {}

    function closeSession(uint256 sessionId) public {
        emit CloseSession(sessionId);
    }

}