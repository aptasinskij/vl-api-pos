pragma solidity 0.4.24;

import "../Ownable.sol";
import "../registry/Component.sol";

contract ACashChannelsManager is Ownable, Component {

    string constant COMPONENT_NAME = "cash-channels-manager";

    enum CashInStatus {CREATING, ACTIVE, FAILED_TO_CREATE, CLOSE_REQUESTED, CLOSED, FAILED_TO_CLOSE}

    modifier cashInActive(uint256 _channelId) {
        require(_cashInStorage().getStatus(_channelId) == uint256(CashInStatus.ACTIVE), "CashIn in illegal state");
        _;
    }

    modifier appOwnsChannel(uint256 _channelId, address _application) {
        require(_cashInStorage().getApplication(_channelId) == _application, "Illegal access");
        _;
    }

    modifier channelBelongsToSession(uint256 _channelId, uint256 _sessionId) {
        require(_cashInStorage().getSessionId(_channelId) == _sessionId, "Arguments mismatch");
        _;
    }

    function openCashInChannel(address _application, uint256 _sessionId) public returns (uint256);

    function closeCashInChannel(address _application, uint256 _sessionId, uint256 _channelId, uint256[] fees, address[] parties) public returns (bool);

    function confirmOpen(uint256 channelId) public;

    function confirmClose(uint256 channelId) public;

    function updateCashInBalance(uint256 channelId, uint256 amount) public;

    function balanceOf(address _application, uint256 _channelId) public view returns (uint256);

}