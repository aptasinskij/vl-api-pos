pragma solidity 0.4.24;

import "../repositories/cash-in/ACashInStorage.sol";
import "../repositories/session/ASessionStorage.sol";
import "../repositories/application/AnApplicationStorage.sol";
import "../application/IApplication.sol";
import "../oracles/ICashInOracle.sol";
import "./ACashChannelsManager.sol";

import {SafeMath} from "../libs/Libraries.sol";

import "../Ownable.sol";
import "../registry/Component.sol";

contract CashChannelsManager is ACashChannelsManager, Component {

    string constant COMPONENT_NAME = "cash-channels-manager";

    using SafeMath for uint256;

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

    constructor(address registry) Component(registry) public {}

    function getName() internal pure returns (string name) {
        return COMPONENT_NAME;
    }

    ///@dev cash-in channel could be created only if:
    ///session is active; application owns the session; there is no active channels in the session
    function openCashInChannel(address _application, uint256 _sessionId) public returns (uint256 channelId) {
        require(_sessionManager().isActive(_sessionId), "Illegal state of the session");
        require(!_sessionStorage().isHasActiveCashIn(_sessionId), "There is already opened cash-in channel");
        _sessionStorage().setHasActiveCashIn(_sessionId, true);
        address application = _applicationStorage().getApplicationAddress(_sessionStorage().getAppId(_sessionId));
        require(application == _application, "Illegal access");
        channelId = _cashInStorage().save(_sessionId, application, uint256(CashInStatus.CREATING));
        _cashInOracle().open(_sessionId, channelId, uint256(CashInStatus.CREATING));
    }

    function updateCashInBalance(uint256 channelId, uint256 amount) public cashInActive(channelId) {
        uint256 currentBalance = _cashInStorage().getBalance(channelId);
        _cashInStorage().setBalance(channelId, currentBalance + amount);
        (address application, uint256 sessionId) = _cashInStorage().getApplicationAndSessionId(channelId);
        IApplication(application).cashInBalanceUpdate(channelId, sessionId, amount);
    }

    function balanceOf(address _application, uint256 _channelId) public view returns (uint256) {
        ACashInStorage cashInStorage = _cashInStorage();
        require(cashInStorage.getApplication(_channelId) == _application, "Illegal access");
        return cashInStorage.getBalance(_channelId);
    }

    function closeCashInChannel(address _application, uint256 _sessionId, uint256 _channelId, uint256[] fees, address[] parties)
    cashInActive(_channelId)
    appOwnsChannel(_channelId, _application)
    channelBelongsToSession(_channelId, _sessionId)
    public returns (bool){
        require(fees.length == parties.length, "Illegal arguments");
        uint256 channelBalance = _cashInStorage().getBalance(_channelId);
        uint256 vaultLogicFee = channelBalance.mul(_parameterManager().getVLFee()).div(10000);
        uint256 feesAmount = _sumOf(fees);
        require(feesAmount.add(vaultLogicFee) <= channelBalance, "Channel balance overflow");
        _cashInStorage().setVLFee(_channelId, vaultLogicFee);
        _cashInStorage().setApplicationBalance(_channelId, channelBalance.sub(vaultLogicFee).sub(feesAmount));
        _cashInStorage().addSplits(_channelId, parties, fees);
        _cashInStorage().setStatus(_channelId, uint256(CashInStatus.CLOSE_REQUESTED));
        _cashInOracle().close(_sessionId, _channelId);
        return true;
    }

    function _sumOf(uint256[] _elements) private pure returns (uint256 _sum) {
        for (uint256 i = 0; i < _elements.length; i++) _sum = _sum.add(_elements[i]);
    }

    function _transferAll(address[] _recepients, uint256[] _amounts) private {
        for (uint256 j = 0; j < _recepients.length; j++) _transfer(_recepients[j], _amounts[j]);
    }

    function _transfer(address _recepient, uint256 amount) private {
        _tokenManager().transfer(_recepient, amount);
    }

    function confirmOpen(uint256 channelId) public {
        require(_cashInStorage().getStatus(channelId) == uint256(CashInStatus.CREATING));
        (address application, uint256 sessionId) = _cashInStorage().getApplicationAndSessionId(channelId);
        _cashInStorage().setStatus(channelId, uint256(CashInStatus.ACTIVE));
        IApplication(application).cashInChannelOpened(channelId, sessionId);
    }

    function confirmClose(uint256 channelId) public {
        require(_cashInStorage().getStatus(channelId) == uint256(CashInStatus.CLOSE_REQUESTED));
        (address application, uint256 sessionId) = _cashInStorage().getApplicationAndSessionId(channelId);
        _transfer(owner, _cashInStorage().getVLFee(channelId));
        _transfer(application, _cashInStorage().getApplicationBalance(channelId));
        for (uint256 i = 0; i < _cashInStorage().getSplitSize(channelId); i++) {
            (address party, uint256 fee) = _cashInStorage().getSplit(channelId, i);
            _transfer(party, fee);
        }
        _cashInStorage().setStatus(channelId, uint256(CashInStatus.CLOSED));
        _sessionStorage().setHasActiveCashIn(sessionId, false);
        IApplication(application).cashInChannelClosed(channelId, sessionId);
    }

}