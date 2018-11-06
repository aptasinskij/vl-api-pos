pragma solidity 0.4.24;

library SessionLib {

    enum Status {CREATING, ACTIVE, FAILED_TO_CREATE, CLOSE_REQUESTED, CLOSED}

    struct Session {
        uint256 id;
        uint256 applicationId;
        string kioskId;
        string xToken;
        Status status;
        bool hasActiveCashIn;
        bool hasActiveCashOut;
    }

    struct Close {
        function(uint256) external success;
        function(uint256) external fail;
    }

}
