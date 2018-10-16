pragma solidity 0.4.24;

import {TokenLib} from "../libs/Libraries.sol";
import {ATokenStorage} from "./Storages.sol";
import "../Platform.sol";

contract TokenStorage is ATokenStorage, Named("token-storage"), Mortal, Component {

    using TokenLib for address;

    constructor(address _config) Component(_config) public {}

    function set(address customer, uint256 amount) public {
        database.set(customer, amount);
    }

    function get(address consumer) public view returns (uint256) {
        return database.get(consumer);
    }

}