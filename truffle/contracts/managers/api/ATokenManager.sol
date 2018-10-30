pragma solidity 0.4.24;

contract ATokenManager {

    event Transfer(address indexed to, uint value);

    event TransferFrom(address indexed from, address indexed to, uint value);

    function balanceOf(address _owner) public view returns (uint256);

    function transfer(address _recipient, uint _value) public;

    function transferFrom(address _from, address _to, uint _value) public;

}
