pragma solidity ^0.4.0;

interface ITokenStorage {

    function set(address customer, uint256 amount) public;

    function get(address consumer) public view returns (uint256);

}