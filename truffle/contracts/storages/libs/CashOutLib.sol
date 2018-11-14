pragma solidity 0.4.24;

library CashOutLib {

    enum Status {
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
        uint256 sessionId;
        uint256 amount;
        function(uint256, uint256) external fail;
        function(uint256, uint256) external success;
    }

    struct CashOut {
        uint256 sessionId;
        address application;
        Status status;
    }

    struct Account {
    }

    struct Close {
        uint256 sessionId;
        function(uint256, uint256) external fail;
        function(uint256, uint256) external success;
    }

    struct Split {
        uint256[] fees;
        address[] parties;
    }

    struct Cassette {
        uint256[] bills;
        uint256[] amounts;
    }

}
