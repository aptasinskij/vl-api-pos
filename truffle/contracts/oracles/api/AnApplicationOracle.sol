pragma solidity 0.4.24;

contract AnApplicationOracle {

    event ApplicationRegistered(uint256 index, string name, address owner, string url, address appAddr, uint256 status);

    function register(uint256 index, string name, address owner, string url, address appAddr, uint256 status) public;

}
