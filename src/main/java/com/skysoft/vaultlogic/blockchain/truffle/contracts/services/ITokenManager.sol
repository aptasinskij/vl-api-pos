pragma solidity ^0.4.0;

interface ITokenManager {

    function balanceOf(address _owner) constant returns (uint balance);

    function transfer(address _recipient, uint _value) onlyPayloadSize(2*32);

    function transferFrom(address _from, address _to, uint _value);

}
