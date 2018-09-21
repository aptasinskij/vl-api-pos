pragma solidity 0.4.24;

contract IParameterStorage {

    function setVLFee(address customer, uint256 percent) external;

    function getVLFee(address consumer) external view returns (uint256);

}