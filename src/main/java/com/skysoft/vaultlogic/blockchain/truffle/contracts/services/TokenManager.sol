pragma solidity 0.4.24;

import "../library/SafeMath.sol";
import "../registry/RegistryComponent.sol";
import "../repositories/token/ITokenStorage.sol";

contract TokenManager is RegistryComponent {

    string constant COMPONENT_NAME = "token-manager";
    string constant TOKEN_STORAGE = "token-storage";

    event Transfer(address indexed to, uint value);
    event TransferFrom(address indexed from, address indexed to, uint value);

    using SafeMath for uint256;

    constructor(address registryAddress) RegistryComponent(registryAddress) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function balanceOf(address consumer) view returns (uint) {
        return ITokenStorage(lookup(TOKEN_STORAGE)).get(consumer);
    }

    function transfer(address recipient, uint value) {
        ITokenStorage tokenStorage = ITokenStorage(lookup(TOKEN_STORAGE));

        uint256 recipientAmount = tokenStorage.get(recipient);
        recipientAmount = recipientAmount.add(value);
        tokenStorage.set(recipient, recipientAmount);

        emit Transfer(recipient, value);
    }

    function transferFrom(address from, address to, uint value) {
        ITokenStorage tokenStorage = ITokenStorage(lookup(TOKEN_STORAGE));

        uint256 balanceFrom = tokenStorage.get(from);
        uint256 balanceTo = tokenStorage.get(to);
        require(balanceFrom >= value && value > 0);

        uint256 amountFrom = tokenStorage.get(from);
        amountFrom = amountFrom.sub(value);
        tokenStorage.set(from, amountFrom);

        uint256 amountTo = tokenStorage.get(to);
        amountTo = amountTo.add(value);
        tokenStorage.set(to, amountTo);

        emit TransferFrom(from, to, value);
    }

}