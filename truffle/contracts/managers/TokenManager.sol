pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "./api/ATokenManager.sol";
import "../platform/Component.sol";
import "../storages/api/ATokenStorage.sol";
import {SafeMath} from "../libs/Libraries.sol";

contract TokenManager is ATokenManager, Named("token-manager"), Mortal, Component {

    string constant STORAGE = "token-storage";

    using SafeMath for uint256;

    constructor(address _config) Component(_config) public {}

    function balanceOf(address consumer) public view returns (uint256) {
        return ATokenStorage(context.get(STORAGE)).get(consumer);
    }

    function transfer(address recipient, uint256 value) public {
        require(value > 0, "The value for the transaction cannot be equals to zero");
        ATokenStorage(context.get(STORAGE)).set(recipient, ATokenStorage(context.get(STORAGE)).get(recipient).add(value));
        emit Transfer(recipient, value);
    }

    function transferFrom(address from, address to, uint256 value) public {
        uint256 balanceFrom = ATokenStorage(context.get(STORAGE)).get(from);
        uint256 balanceTo = ATokenStorage(context.get(STORAGE)).get(to);
        require(balanceFrom >= value && value > 0, "Transaction parameters are not correct");
        ATokenStorage(context.get(STORAGE)).set(from, balanceFrom.sub(value));
        ATokenStorage(context.get(STORAGE)).set(to, balanceTo.add(value));
        emit TransferFrom(from, to, value);
    }

}