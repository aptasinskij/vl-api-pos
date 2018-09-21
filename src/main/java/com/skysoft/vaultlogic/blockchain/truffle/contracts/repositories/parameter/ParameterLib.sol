pragma solidity 0.4.24;

import "../../storage/Database.sol";

library ParameterLib {

    string constant VAULT_LOGIC_FEE_PERCENT = "vault_logic_fee_percent";

    function setVLFee(address self, uint256 percent) internal {
        Database(self).setUintValue(keccak256(abi.encode(VAULT_LOGIC_FEE_PERCENT)), percent);
    }

    function getVLFee(address self) internal view returns (uint256) {
        return Database(self).getUintValue(keccak256(abi.encode(VAULT_LOGIC_FEE_PERCENT)));
    }

}