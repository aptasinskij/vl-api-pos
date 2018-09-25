pragma solidity 0.4.24;

contract ACashChannelsManager {

    enum CashInStatus {CREATING, ACTIVE, FAILED_TO_CREATE, CLOSE_REQUESTED, CLOSED, FAILED_TO_CLOSE}

    function openCashInChannel(address _application, uint256 _sessionId) public returns (uint256);

    function closeCashInChannel(address _application, uint256 _sessionId, uint256 _channelId, uint256[] fees, address[] parties) public returns (bool);

    function confirmOpen(uint256 channelId) public;

    function confirmClose(uint256 channelId) public;

    function updateCashInBalance(uint256 channelId, uint256 amount) public;

    function balanceOf(address _application, uint256 _channelId) public view returns (uint256);

}

contract AnApplicationManager {

    enum ApplicationStatus { PENDING, ENABLED, DISABLED }

    function registerApplication(uint256 appId, string name, address owner, string url, address appAddr) public;

    function enableApplication(uint256 appId) public;

}

contract AParameterManager {

    function setVLFee(uint256 percent) public;

    function getVLFee() public view returns (uint256);

}

contract ASessionManager {

    function createSession(uint256 sessionId, uint256 appId, string xToken) public;

    function closeSession(uint256 sessionId) public;

    function confirmClose(uint256 sessionId) public;

    function isActive(uint256 sessionId) public view returns(bool);

    function isHasActiveCashIn(uint256 _sessionId) public view returns(bool);

    function activate(uint256 _sessionId) public returns(bool);

}

contract ATokenManager {

    event Transfer(address indexed to, uint value);

    event TransferFrom(address indexed from, address indexed to, uint value);

    function balanceOf(address _owner) public view returns (uint256);

    function transfer(address _recipient, uint _value) public;

    function transferFrom(address _from, address _to, uint _value) public;

}