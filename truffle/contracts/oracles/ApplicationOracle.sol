pragma solidity 0.4.24;

import "../platform/Mortal.sol";
import "../platform/Named.sol";
import "../platform/Component.sol";
import "./api/AnApplicationOracle.sol";

contract ApplicationOracle is AnApplicationOracle, Mortal, Named("application-oracle"), Component {

    constructor(address _config) Component(_config) public {}

    function register(
        uint256 index,
        string name,
        address owner,
        string url,
        address appAddr,
        uint256 status
    ) public {
        emit ApplicationRegistered(index, name, owner, url, appAddr, status);
    }

}