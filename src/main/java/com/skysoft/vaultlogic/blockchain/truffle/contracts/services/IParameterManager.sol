pragma solidity 0.4.24;

contract IParameterManager {

    function setVLFee(uint256 percent) public;

    function getVLFee() public view returns (uint256);

}
