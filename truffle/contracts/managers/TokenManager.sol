pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "./api/ATokenManager.sol";
import "../platform/Component.sol";
import "../storages/api/ATokenStorage.sol";
import "../storages/api/ACashInStorage.sol";
import {SafeMath} from "../libs/Libraries.sol";

contract TokenManager is ATokenManager, Named("token-manager"), Mortal, Component {

    string constant TOKEN_STORAGE = "token-storage";
    string constant CASH_IN_STORAGE = "cash-in-storage";

    mapping(address => uint256) private tokens;

    using SafeMath for uint256;

    constructor(address _config) Component(_config) public {}

    function balanceOf(address consumer) public view returns (uint256) {
        return ATokenStorage(context.get(TOKEN_STORAGE)).get(consumer);
    }

    function transfer(address recipient, uint256 value) public {
        require(value > 0, "The value for the transaction cannot be equals to zero");
        ATokenStorage(context.get(TOKEN_STORAGE)).set(recipient, ATokenStorage(context.get(TOKEN_STORAGE)).get(recipient).add(value));
        emit Transfer(recipient, value);
    }

    function transferFrom(address from, address to, uint256 value) public {
        uint256 balanceFrom = ATokenStorage(context.get(TOKEN_STORAGE)).get(from);
        uint256 balanceTo = ATokenStorage(context.get(TOKEN_STORAGE)).get(to);
        require(balanceFrom >= value && value > 0, "Transaction parameters are not correct");
        ATokenStorage(context.get(TOKEN_STORAGE)).set(from, balanceFrom.sub(value));
        ATokenStorage(context.get(TOKEN_STORAGE)).set(to, balanceTo.add(value));
        emit TransferFrom(from, to, value);
    }

    function performCashInTransfer(uint256 _commandId) public {
        ACashInStorage cashInStorage = ACashInStorage(context.get(CASH_IN_STORAGE));
        address application = cashInStorage.retrieveCashInApplication(_commandId);
        (uint256 _balance,,uint256 _fee) = cashInStorage.retrieveAccount(_commandId);
        uint256 vlFee = (_balance * _fee) / 100;
        uint256 appFee = _balance - vlFee;
        //TODO: add validation for vlFee and appFee
//      tokens[vl_Address] = tokens[vl_Address] + vlFee;
        tokens[application] = tokens[application] + appFee;
    }

}