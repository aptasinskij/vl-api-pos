pragma solidity 0.4.24;

import "../registry/Component.sol";
import {SafeMath} from "../libs/Libraries.sol";
import {ATokenManager} from "./Managers.sol";

contract TokenManager is Component, ATokenManager {

    string constant COMPONENT_NAME = "token-manager";

    using SafeMath for uint256;

    constructor(address registryAddress) Component(registryAddress) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function balanceOf(address consumer) public view returns (uint256) {
        return _tokenStorage().get(consumer);
    }

    function transfer(address recipient, uint256 value) public {
        require(value > 0, "The value for the transaction cannot be equals to zero");
        _tokenStorage().set(recipient, _tokenStorage().get(recipient).add(value));
        emit Transfer(recipient, value);
    }

    function transferFrom(address from, address to, uint256 value) public {
        uint256 balanceFrom = _tokenStorage().get(from);
        uint256 balanceTo = _tokenStorage().get(to);
        require(balanceFrom >= value && value > 0, "Transaction parameters are not correct");
        _tokenStorage().set(from, balanceFrom.sub(value));
        _tokenStorage().set(to, balanceTo.add(value));
        emit TransferFrom(from, to, value);
    }

}