pragma solidity 0.4.24;

import "../../storage/Database.sol";

library CashInLib {

    bytes32 constant CASH_IN_INDEX = keccak256(abi.encode("CashInIndex"));

    string constant SESSION_ID = "cash_in_session_id";
    string constant APPLICATION = "cash_in_application";
    string constant STATUS = "cash_in_status";
    string constant BALANCE = "cash_in_balance";
    string constant VL_FEE = "cash_in_vault_logic_fee";
    string constant APP_BALANCE = "cash_in_application_balance";

    ///@dev set of constants to store and retrieve dynamic sized arrays of splits
    string constant SPLIT_SIZE = "cash_in_split_size";
    string constant SPLIT_PARTIES = "cash_in_split_parties";
    string constant SPLIT_FEES = "cash_in_split_fees";

    function getCounter(address self) public view returns(uint256) {
        Database(self).getUintValue(CASH_IN_INDEX);
    }

    function save(address self, uint256 sessionId, address application, uint256 status) internal returns(uint256 index) {
        index = Database(self).getUintValue(CASH_IN_INDEX);
        Database(self).setUintValue(string256(SESSION_ID, index), sessionId);
        Database(self).setAddressValue(string256(APPLICATION, index), application);
        Database(self).setUintValue(string256(STATUS, index), status);
        Database(self).setUintValue(CASH_IN_INDEX, index + 1);
    }

    /// @dev size of both arrays (e.g. parties and fees) stored in separate field 'splitSize'
    function get(address self, uint256 index) public view returns(
        uint256 sessionId,
        address application,
        uint256 balance,
        uint256 status, 
        uint256 splitSize
    ) {
        sessionId = Database(self).getUintValue(string256(SESSION_ID, index));
        application = Database(self).getAddressValue(string256(APPLICATION, index));
        balance = Database(self).getUintValue(string256(BALANCE, index));
        status = Database(self).getUintValue(string256(STATUS, index));
        splitSize = Database(self).getUintValue(string256(SPLIT_SIZE, index));
    }

    function getSessionId(address self, uint256 index) public view returns(uint256) {
        return Database(self).getUintValue(string256(SESSION_ID, index));
    }

    function getApplication(address self, uint256 index) public view returns(address) {
        return Database(self).getAddressValue(string256(APPLICATION, index));
    }

    function setBalance(address self, uint256 index, uint256 amount) internal {
        Database(self).setUintValue(string256(BALANCE, index), amount);
    }

    function getBalance(address self, uint256 index) public view returns (uint256) {
        return Database(self).getUintValue(string256(BALANCE, index));
    }

    function setVLFee(address self, uint256 index, uint256 fee) internal {
        Database(self).setUintValue(keccak256(abi.encodePacked(VL_FEE, index)), fee);
    }

    function getVLFee(address self, uint256 index) internal view returns(uint256) {
        return Database(self).getUintValue(keccak256(abi.encodePacked(VL_FEE, index)));
    }

    function setApplicationBalance(address self, uint256 index, uint256 balance) internal {
        Database(self).setUintValue(string256(APP_BALANCE, index), balance);
    }

    function getApplicationBalance(address self, uint256 index) internal view returns(uint256) {
        return Database(self).getUintValue(string256(APP_BALANCE, index));
    }

    function setStatus(address self, uint256 index, uint256 status) internal {
        Database(self).setUintValue(string256(STATUS, index), uint256(status));
    }

    function getStatus(address self, uint256 index) public view returns(uint256) {
        return Database(self).getUintValue(string256(STATUS, index));
    }

    function addSplit(address self, uint256 index, address receiver, uint256 amount) internal {
        uint256 splitSize = Database(self).getUintValue(string256(SPLIT_SIZE, index));
        Database(self).setAddressValue(string256x2(SPLIT_PARTIES, index, splitSize), receiver);
        Database(self).setUintValue(string256x2(SPLIT_FEES, index, splitSize), amount);
        Database(self).setUintValue(string256(SPLIT_SIZE, index), splitSize + 1);
    }

    function addSplits(address self, uint256 index, address[] receivers, uint256[] amounts) internal {
        for (uint256 i = 0; i < receivers.length; i++) {
            addSplit(self, index, receivers[i], amounts[i]);
        }
    }

    function getSplitSize(address self, uint256 index) public view returns(uint256) {
        return Database(self).getUintValue(string256(SPLIT_SIZE, index));
    }

    function getSplit(address self, uint256 index, uint256 subIndex) public view returns(address party, uint256 fee) {
        party = Database(self).getAddressValue(string256x2(SPLIT_PARTIES, index, subIndex));
        fee = Database(self).getUintValue(string256x2(SPLIT_FEES, index, subIndex));
    }

    function string256(string field, uint256 index) private pure returns(bytes32) {
        return keccak256(abi.encodePacked(field, index));
    }

    function string256x2(string field, uint256 index, uint256 counter) private pure returns(bytes32) {
        return keccak256(abi.encodePacked(field, index, counter));
    }
    
}