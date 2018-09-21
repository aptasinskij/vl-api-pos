pragma solidity 0.4.24;

import "../../storage/Database.sol";

library ParameterLib {

    string constant VAULT_LOGIC_FEE_PERCENT = "vault_logic_fee_percent";

    function setVLFee(address self, address customer, uint256 percent) internal {
        Database(self).setUintValue(keccak256(abi.encodePacked(VAULT_LOGIC_FEE_PERCENT, customer)), percent);
    }

    function getVLFee(address self, address customer) internal view returns (uint256) {
        return Database(self).getUintValue(keccak256(abi.encodePacked(VAULT_LOGIC_FEE_PERCENT, customer)));
    }

}