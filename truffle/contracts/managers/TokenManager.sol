pragma solidity 0.4.24;

import "../registry/Component.sol";
import {SafeMath} from "../libs/Libraries.sol";
import {ATokenManager} from "./Managers.sol";
import {ATokenStorage} from "../storages/Storages.sol";

contract TokenManager is Component, ATokenManager {

    string constant COMPONENT_NAME = "token-manager";

    using SafeMath for uint256;

    constructor(address registryAddress) Component(registryAddress) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function balanceOf(address consumer) public view returns (uint256) {
        return ATokenStorage(_tokenStorage()).get(consumer);
    }

    function transfer(address recipient, uint256 value) public {
        require(value > 0, "The value for the transaction cannot be equals to zero");
        ATokenStorage(_tokenStorage()).set(recipient, ATokenStorage(_tokenStorage()).get(recipient).add(value));
        emit Transfer(recipient, value);
    }

    function transferFrom(address from, address to, uint256 value) public {
        uint256 balanceFrom = ATokenStorage(_tokenStorage()).get(from);
        uint256 balanceTo = ATokenStorage(_tokenStorage()).get(to);
        require(balanceFrom >= value && value > 0, "Transaction parameters are not correct");
        ATokenStorage(_tokenStorage()).set(from, balanceFrom.sub(value));
        ATokenStorage(_tokenStorage()).set(to, balanceTo.add(value));
        emit TransferFrom(from, to, value);
    }

}