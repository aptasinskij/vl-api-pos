pragma solidity ^0.4.0;

import "../registry/RegistryComponent.sol";
import "../repositories/token/ITokenStorage.sol";

contract TokenManager is RegistryComponent {

    string constant COMPONENT_NAME = "token-manager";

    string constant TOKEN_STORAGE = "token-storage";

    constructor(address registryAddress) RegistryComponent(registryAddress) public {}

    modifier onlyPayloadSize(uint size) {
        assert(msg.data.length == size + 4);
        _;
    }

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function balanceOf(address consumer) constant returns (uint balance) {
        uint256 balance = ITokenStorage(lookup(TOKEN_STORAGE)).get(consumer);
    }

    function transfer(address recipient, uint value) onlyPayloadSize(2*32) {
        uint256 recipientAmount = ITokenStorage(lookup(TOKEN_STORAGE)).get(recipient);
        recipientAmount += value;
        ITokenStorage(lookup(TOKEN_STORAGE)).set(recipient, recipientAmount);

        emit Transfer(msg.sender, recipient, value);
    }

    function transferFrom(address from, address to, uint value) {
        uint256 balanceFrom = ITokenStorage(lookup(TOKEN_STORAGE)).get(from);
        uint256 balanceTo = ITokenStorage(lookup(TOKEN_STORAGE)).get(to);
        require(balanceFrom >= value && value > 0);

        uint256 amountFrom = ITokenStorage(lookup(TOKEN_STORAGE)).get(from);
        amountFrom -= value;
        ITokenStorage(lookup(TOKEN_STORAGE)).set(from, amountFrom);

        uint256 amountTo = ITokenStorage(lookup(TOKEN_STORAGE)).get(to);
        amountTo += value;
        ITokenStorage(lookup(TOKEN_STORAGE)).set(to, amountTo);

        emit Transfer(from, to, value);
    }

    event Transfer(address indexed from, address indexed to, uint value);

}