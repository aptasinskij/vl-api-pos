pragma solidity 0.4.24;

import "../../registry/Component.sol";

import {TokenLib} from "../../libs/Libraries.sol";
import {ATokenStorage} from "../../Storages.sol";

contract TokenStorage is Component, ATokenStorage {

    string constant COMPONENT_NAME = "token-storage";
    string constant DATABASE = "database";

    using TokenLib for address;

    constructor(address registryAddress) Component(registryAddress) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function set(address customer, uint256 amount) public {
        lookup(DATABASE).set(customer, amount);
    }

    function get(address consumer) public view returns (uint256) {
        return lookup(DATABASE).get(consumer);
    }

}