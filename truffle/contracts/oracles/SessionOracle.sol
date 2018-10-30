pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "./api/ASessionOracle.sol";
import "../platform/Component.sol";

contract SessionOracle is ASessionOracle, Named("session-oracle"), Mortal, Component  {

    constructor(address _config) Component(_config) public {}

    function closeSession(uint256 sessionId) public {
        emit CloseSession(sessionId);
    }

}