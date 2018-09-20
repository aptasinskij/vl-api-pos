pragma solidity 0.4.24;

import "../libs/SafeMath.sol";
import "../registry/RegistryComponent.sol";
import "../repositories/token/ITokenStorage.sol";
import "./ITokenManager.sol";

contract TokenManager is RegistryComponent, ITokenManager {

    string constant COMPONENT_NAME = "token-manager";

    event Transfer(address indexed to, uint value);
    event TransferFrom(address indexed from, address indexed to, uint value);

    using SafeMath for uint256;

    constructor(address registryAddress) RegistryComponent(registryAddress) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function balanceOf(address consumer) external view returns (uint) {
        return _tokenStorage().get(consumer);
    }

    function transfer(address recipient, uint value) external {
        ITokenStorage tokenStorage = _tokenStorage();
        tokenStorage.set(recipient, tokenStorage.get(recipient).add(value));
        emit Transfer(recipient, value);
    }

    function transferFrom(address from, address to, uint value) external {
        ITokenStorage tokenStorage = _tokenStorage();

        uint256 balanceFrom = tokenStorage.get(from);
        uint256 balanceTo = tokenStorage.get(to);
        require(balanceFrom >= value && value > 0);

        tokenStorage.set(from, balanceFrom.sub(value));
        tokenStorage.set(to, balanceTo.add(value));
        emit TransferFrom(from, to, value);
    }

}