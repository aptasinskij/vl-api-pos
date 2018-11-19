pragma solidity 0.4.24;

library CashOutLib {

    enum Status {
        CREATING,
        ACTIVE,
        VALIDATING,
        ABLE_TO_CLOSE,
        NOT_ABLE_TO_CLOSE,
        CLOSE_REQUESTED,
        CLOSED,
        FAILED_TO_CLOSE,
        CANCEL
    }

    struct Open {
        string kioskId;
        function(string memory) external fail;
        function(string memory, uint256) external success;
    }

    struct CashOut {
        address application;
        Status status;
    }

    struct Account {
        uint256 toWithdraw;
        uint256 VLFee;
        uint256 reserve;
        uint256[] fees;
        address[] parties;
    }

    struct Validate {
        uint256 sessionId;
        function(uint256, uint256) external fail;
        function(uint256, uint256) external success;
    }

    struct Close {
        uint256 sessionId;
        function(uint256, uint256) external fail;
        function(uint256, uint256) external success;
    }

    struct Rollback {
        function(uint256) external fail;
        function(uint256) external success;
    }

}