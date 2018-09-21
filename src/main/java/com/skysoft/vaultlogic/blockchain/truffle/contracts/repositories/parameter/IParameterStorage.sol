pragma solidity 0.4.24;

interface IParameterStorage {

    function setVLFee(uint256 percent) external;

    function getVLFee() external view returns (uint256);

}