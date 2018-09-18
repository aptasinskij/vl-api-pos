pragma solidity 0.4.24;

import "./Database.sol";

library TokenLib {

    string constant TOKEN_AMOUNT = "token_amount";

    function set(address self, address customer, uint256 amount) internal {
        Database(self).setUintValue(keccak256(abi.encodePacked(TOKEN_AMOUNT, customer)), amount);
    }

    function get(address self, address customer) internal view returns (uint256) {
        Database(self).getUintValue(keccak256(abi.encodePacked(TOKEN_AMOUNT, customer)));
    }

}