pragma solidity 0.4.24;

interface ITokenManager {

    function balanceOf(address _owner) external view returns (uint256);

    function transfer(address _recipient, uint _value) external;

    function transferFrom(address _from, address _to, uint _value) external;

}
