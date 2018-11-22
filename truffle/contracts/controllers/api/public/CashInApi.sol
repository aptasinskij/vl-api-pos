pragma solidity 0.4.24;

interface CashInApi {

    /**
    @notice Get VaultLogic fee percent for CashIn channel
    @dev
    @param
    @return {
      "_fee": "The VaultLogic fee percent"
    }
    */
    // @formatter:off
    function getFeePercent() external view returns (uint256);
    // @formatter:on


    /**
    @notice Create CashIn channel
    @dev
    @param _sessionId: id of the session in which CashIn channel creating
    @param _maxBalance: the max amount of user's money which can be in CashIn channel
    @param _fail: callback function(_sessionId) for informing about failed case of creating CashIn channel
    @param _success: callback function(_sessionId, _maxBalance) for informing about successful case of creating CashIn channel
    @param _update: callback function(_sessionId, _maxBalance, _amount) for informing about updating CashIn channel...
    @return {
    }
    */
    // @formatter:off
    function openCashInChannel(
        uint256 _sessionId,
        uint256 _maxBalance,
        function(uint256) external _fail,
        function(uint256, uint256) external _success,
        function(uint256, uint256, uint256) external _update
    )
        external;
    // @formatter:on

    /**
    @notice Close CashIn channel
    @dev
    @param _sessionId: id of the session in which CashIn channel closing
    @param _channelId: id of the CashIn channel to close
    @param _fees: list of fees for every application
    @param _parties: list of addresses for every application
    @param _success: callback function(_sessionId, _channelId) for informing about successful case of closing CashIn channel
    @param _fail: callback function(_sessionId, _channelId) for informing about failed case of closing CashIn channel
    @return {
    }
    */
    // @formatter:off
    function closeCashInChannel(
        uint256 _sessionId,
        uint256 _channelId,
        uint256[] _fees,
        address[] _parties,
        function(uint256, uint256) external _success,
        function(uint256, uint256) external _fail
    )
        external;
    // @formatter:on

    /**
    @notice Get balance of the channel
    @dev
    @param _channelId: id of the CashIn channel from which we want to get balance
    @return {
        "_balance": "The balance of the channel"
    }
    */
    // @formatter:off
    function balanceOf(
        uint256 _channelId
    )
        external
        view
        returns (
            uint256 _balance
        );
    // @formatter:on
}
