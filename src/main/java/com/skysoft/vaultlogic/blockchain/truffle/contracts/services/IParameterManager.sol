pragma solidity 0.4.24;

interface IParameterManager {

    function setVLFee(address customer, uint256 percent) external;

    function getVLFee(address consumer) external view returns (uint256);

}
