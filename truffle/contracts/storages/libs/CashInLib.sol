pragma solidity 0.4.24;

library CashInLib {

    enum Status {
        CREATING,
        ACTIVE,
        FAILED_TO_CREATE,
        CLOSE_REQUESTED,
        CLOSED,
        FAILED_TO_CLOSE
    }

    struct Open {
        uint256 sessionId;
        uint256 maxBalance;
        function(uint256) external fail;
        function(uint256, uint256) external success;
        function(uint256, uint256, uint256) external update;
    }

    struct CashIn {
        uint256 sessionId;
        address application;
        Status status;
    }

    struct Account {
        uint256 balance;
        uint256 maxBalance;
        uint256 fee;
    }

    struct Close {
        uint256 sessionId;
        function(uint256, uint256) external success;
        function(uint256, uint256) external fail;
    }

    struct Split {
        uint256[] fees;
        address[] parties;
    }

}
