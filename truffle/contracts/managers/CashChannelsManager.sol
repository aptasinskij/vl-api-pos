pragma solidity 0.4.24;

import "../application/IApplication.sol";
import {ACashInOracle} from "../oracles/Oracles.sol";

import {SafeMath} from "../libs/Libraries.sol";
import {ACashChannelsManager, ASessionManager, ATokenManager} from "./Managers.sol";

import "../registry/Component.sol";
import "../storages/Storages.sol";
import {Owned} from "../Platform.sol";

contract CashChannelsManager is ACashChannelsManager, Component, Owned {

    string constant COMPONENT_NAME = "cash-channels-manager";

    using SafeMath for uint256;

    modifier cashInActive(uint256 _channelId) {
        require(ACashInStorage(_cashInStorage()).getStatus(_channelId) == uint256(CashInStatus.ACTIVE), "CashIn in illegal state");
        _;
    }

    modifier appOwnsChannel(uint256 _channelId, address _application) {
        require(ACashInStorage(_cashInStorage()).getApplication(_channelId) == _application, "Illegal access");
        _;
    }

    modifier channelBelongsToSession(uint256 _channelId, uint256 _sessionId) {
        require(ACashInStorage(_cashInStorage()).getSessionId(_channelId) == _sessionId, "Arguments mismatch");
        _;
    }

    constructor(address registry) Component(registry) public {}

    function getName() internal pure returns (string name) {
        return COMPONENT_NAME;
    }

    ///@dev cash-in channel could be created only if:
    ///session is active; application owns the session; there is no active channels in the session
    function openCashInChannel(address _application, uint256 _sessionId) public returns (uint256 channelId) {
        require(ASessionManager(_sessionManager()).isActive(_sessionId), "Illegal state of the session");
        require(!ASessionStorage(_sessionStorage()).isHasActiveCashIn(_sessionId), "There is already opened cash-in channel");
        ASessionStorage(_sessionStorage()).setHasActiveCashIn(_sessionId, true);
        address application = AnApplicationStorage(_applicationStorage()).getApplicationAddress(ASessionStorage(_sessionStorage()).getAppId(_sessionId));
        require(application == _application, "Illegal access");
        channelId = ACashInStorage(_cashInStorage()).save(_sessionId, application, uint256(CashInStatus.CREATING));
        ACashInOracle(_cashInOracle()).open(_sessionId, channelId, uint256(CashInStatus.CREATING));
    }

    function updateCashInBalance(uint256 channelId, uint256 amount) public cashInActive(channelId) {
        uint256 currentBalance = ACashInStorage(_cashInStorage()).getBalance(channelId);
        ACashInStorage(_cashInStorage()).setBalance(channelId, currentBalance + amount);
        (address application, uint256 sessionId) = ACashInStorage(_cashInStorage()).getApplicationAndSessionId(channelId);
        IApplication(application).cashInBalanceUpdate(channelId, amount, sessionId);
    }

    function balanceOf(address _application, uint256 _channelId) public view returns (uint256) {
        ACashInStorage cashInStorage = ACashInStorage(_cashInStorage());
        require(cashInStorage.getApplication(_channelId) == _application, "Illegal access");
        return cashInStorage.getBalance(_channelId);
    }

    function closeCashInChannel(address _application, uint256 _sessionId, uint256 _channelId, uint256[] fees, address[] parties)
    cashInActive(_channelId)
    appOwnsChannel(_channelId, _application)
    channelBelongsToSession(_channelId, _sessionId)
    public returns (bool){
        require(fees.length == parties.length, "Illegal arguments");
        uint256 channelBalance = ACashInStorage(_cashInStorage()).getBalance(_channelId);
        uint256 vaultLogicFee = channelBalance.mul(AParameterStorage(_parameterStorage()).getVLFee()).div(10000);
        uint256 feesAmount = _sumOf(fees);
        require(feesAmount.add(vaultLogicFee) <= channelBalance, "Channel balance overflow");
        ACashInStorage(_cashInStorage()).setVLFee(_channelId, vaultLogicFee);
        ACashInStorage(_cashInStorage()).setApplicationBalance(_channelId, channelBalance.sub(vaultLogicFee).sub(feesAmount));
        ACashInStorage(_cashInStorage()).addSplits(_channelId, parties, fees);
        ACashInStorage(_cashInStorage()).setStatus(_channelId, uint256(CashInStatus.CLOSE_REQUESTED));
        ACashInOracle(_cashInOracle()).close(_sessionId, _channelId);
        return true;
    }

    function _sumOf(uint256[] _elements) private pure returns (uint256 _sum) {
        for (uint256 i = 0; i < _elements.length; i++) _sum = _sum.add(_elements[i]);
    }

    function _transferAll(address[] _recepients, uint256[] _amounts) private {
        for (uint256 j = 0; j < _recepients.length; j++) _transfer(_recepients[j], _amounts[j]);
    }

    function _transfer(address _recepient, uint256 amount) private {
        ATokenManager(_tokenManager()).transfer(_recepient, amount);
    }

    function confirmOpen(uint256 channelId) public {
        require(ACashInStorage(_cashInStorage()).getStatus(channelId) == uint256(CashInStatus.CREATING));
        (address application, uint256 sessionId) = ACashInStorage(_cashInStorage()).getApplicationAndSessionId(channelId);
        ACashInStorage(_cashInStorage()).setStatus(channelId, uint256(CashInStatus.ACTIVE));
        IApplication(application).cashInChannelOpened(channelId, sessionId);
    }

    function confirmClose(uint256 channelId) public {
        require(ACashInStorage(_cashInStorage()).getStatus(channelId) == uint256(CashInStatus.CLOSE_REQUESTED));
        (address application, uint256 sessionId) = ACashInStorage(_cashInStorage()).getApplicationAndSessionId(channelId);
        _transfer(owner, ACashInStorage(_cashInStorage()).getVLFee(channelId));
        uint256 applicationBalance = ACashInStorage(_cashInStorage()).getApplicationBalance(channelId);
        if (applicationBalance > 0) _transfer(application, applicationBalance);
        for (uint256 i = 0; i < ACashInStorage(_cashInStorage()).getSplitSize(channelId); i++) {
            (address party, uint256 fee) = ACashInStorage(_cashInStorage()).getSplit(channelId, i);
            _transfer(party, fee);
        }
        ACashInStorage(_cashInStorage()).setStatus(channelId, uint256(CashInStatus.CLOSED));
        ASessionStorage(_sessionStorage()).setHasActiveCashIn(sessionId, false);
        IApplication(application).cashInChannelClosed(channelId, sessionId);
    }

}