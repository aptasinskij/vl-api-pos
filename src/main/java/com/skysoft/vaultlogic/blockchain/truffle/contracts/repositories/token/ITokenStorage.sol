pragma solidity 0.4.24;

interface ITokenStorage {

    function set(address customer, uint256 amount) external;

    function get(address consumer) external view returns (uint256);

}