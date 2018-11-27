pragma solidity 0.4.24;

contract CashOutApi {

    /**
    @notice Get VaultLogic fee percent for CashOut channel
    @dev
    @param
    @return {
      _fee "The VaultLogic fee percent"
    }
    */
    // @formatter:off
    function getFeePercent() external view returns (uint256);
    // @formatter:on

    /**
    @notice Create CashOut channel
    @dev CashOut channel will be assigned CREATING status;
         will proceed to either ACTIVE or FAILED_TO_CREATE after successful or fail confirmation respectively
    @param _requestId application assigned request id
    @param _kioskId id of the kiosk in which CashOut channel creating
    @param _toWithdraw the amount of tokens application wants to dispense
    @param _fees array of fee amount per split definition party
    @param _parties array of parties of split definition
    @param _fail callback function(_requestId, _kioskId) for informing about failed case of creating CashOut channel
    @param _success callback function(_requestId, _kioskId, _toWithdraw, _vlFee) for informing about successful case of creating CashOut channel
    @return {
    }
    */
    // @formatter:off
    function openCashOutChannel(
        string _requestId,
        string _kioskId,
        uint256 _toWithdraw,
        uint256[] _fees,
        address[] _parties,
        function(string memory, string memory) external _fail,
        function(string memory, string memory, uint256, uint256) external _success
    )
        public;
    // @formatter:on

    /**
    @notice Validate CashOut channel.
        Require CashOut in ACTIVE, NOT_ABLE_TO_CLOSE or ABLE_TO_CLOSE status
    @dev CashOut channel will be assigned VALIDATING status;
        will proceed to either ABLE_TO_CLOSE or NOT_ABLE_TO_CLOSE after successful or fail confirmation respectively
    @param _sessionId id of the session in which CashOut channel is validating
    @param _cashOutId id of the CashOut channel
    @param _fail callback function(_sessionId, _cashOutId) for informing about failed case of validating CashOut channel
    @param _success callback function(_sessionId, _cashOutId) for informing about successful case of validating CashOut channel
    @return {
    }
    */
    // @formatter:off
    function validateCashOutChannel(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public;
    // @formatter:on

    /**
    @notice Close CashOut channel.
        Require CashOut in ABLE_TO_CLOSE status and validation was in the same session
    @dev CashOut channel will be assigned CLOSE_REQUESTED status;
        will proceed either to CLOSED or FAILED_TO_CLOSE after successful of fail confirmation respectively
    @param _sessionId id of the session in which CashOut channel is closing
    @param _cashOutId id of the CashOut channel
    @param _fail callback function(_sessionId, _cashOutId) for informing about failed case of closing CashOut channel
    @param _success callback function(_sessionId, _cashOutId) for informing about successful case of closing CashOut channel
    @return {
    }
    */
    // @formatter:off
    function closeCashOutChannel(
        uint256 _sessionId,
        uint256 _cashOutId,
        function(uint256, uint256) external _fail,
        function(uint256, uint256) external _success
    )
        public;
    // @formatter:on

    /**
    @notice Rollback CashOut channel.
        Require CashOut in ACTIVE, ABLE_TO_CLOSE or NOT_ABLE_TO_CLOSE status
    @dev CashOut channel will be assigned CANCELED status
    @param _cashOutId id of the CashOut channel
    @param _fail callback function(_cashOutId) for informing about failed case of rollback CashOut channel
    @param _success callback function(_cashOutId) for informing about successful case of rollback CashOut channel
    @return {
    }
    */
    // @formatter:off
    function rollbackCashOutChannel(
        uint256 _cashOutId,
        function(uint256) external _fail,
        function(uint256) external _success
    )
        public;
    // @formatter:on

}