pragma solidity 0.4.24;

contract AnApplicationStorage {

    event ApplicationSaved(uint256 appId, string name, address owner, string url, address appAddr, uint256 status);
    event ApplicationUrlUpdated(uint256 appId, string url);
    event ApplicationAddressUpdated(uint256 appId, address appAddr);
    event ApplicationStatusUpdated(uint256 appId, uint256 status);

    function save(uint256 appId, string name, address owner, string url, address appAddr, uint256 status) public;

    function get(uint256 appId) public view returns (string, address, string, address, uint256);

    function isRegistered(address _applicationAddress) public view returns (bool);

    function getApplicationName(uint256 appId) public view returns (string);

    function getApplicationOwner(uint256 appId) public view returns (address);

    function getApplicationUrl(uint256 appId) public view returns (string);

    function setApplicationUrl(uint256 appId, string url) public;

    function getApplicationAddress(uint256 appId) public view returns (address);

    function setApplicationAddress(uint256 appId, address appAddr) public;

    function getApplicationStatus(uint256 appId) public view returns (uint256);

    function setApplicationStatus(uint256 appId, uint256 status) public;

}

contract ACashInStorage {

    event CashInSaved(uint256 channelId, uint256 sessionId, address application, uint256 status);
    event CashInBalanceUpdated(uint256 channelId, uint256 amount);
    event CashInStatusUpdated(uint256 channelId, uint256 status);
    event CashInSplitAdded(uint256 channelId, address party, uint256 amount);

    function save(uint256 sessionId, address application, uint256 status) public returns (uint256);

    function get(uint256 channelId) public view
    returns (
        uint256 sessionId,
        address application,
        uint256 balance,
        uint256 status,
        uint256 splitSize
    );

    function getSessionId(uint256 channelId) public view returns (uint256);

    function getApplication(uint256 channelId) public view returns (address);

    function getApplicationAndSessionId(uint256 channelId) public view returns (address, uint256);

    function setBalance(uint256 channelId, uint256 amount) public;

    function getBalance(uint256 channelId) public view returns (uint256);

    function setVLFee(uint256 channelId, uint256 fee) public;

    function getVLFee(uint256 channelId) public view returns (uint256);

    function setApplicationBalance(uint256 channelId, uint256 balance) public;

    function getApplicationBalance(uint256 channelId) public view returns (uint256);

    function setStatus(uint256 channelId, uint256 status) public;

    function getStatus(uint256 channelId) public view returns (uint256);

    function addSplit(uint256 channelId, address party, uint256 amount) public;

    function addSplits(uint256 channelId, address[] parties, uint256[] amounts) public;

    function getSplitSize(uint256 channelId) public view returns (uint256);

    function getSplit(uint256 channelId, uint256 subIndex) public view returns (address, uint256);

}

contract AParameterStorage {

    function setVLFee(uint256 percent) public;

    function getVLFee() public view returns (uint256);

}

contract AKioskStorage {

    function createKiosk(string _id, string _location, string _name, string _timezone) public;

    function retrieveKiosk(string _id) public view returns (string memory _location, string memory _name, string memory _timezone);

}

contract ASessionStorage {

    event Saved(uint256 sessionId, uint256 appId, string xToken, uint256 status, string kioskId);
    event StatusUpdated(uint256 index, uint256 status);
    event ActiveCashIn(uint256 _sessionId, bool _flag);

    function getAppId(uint256 index) public view returns (uint256);

    function getXToken(uint256 index) public view returns (string xToken);

    function getAppIdAndXToken(uint256 index) public view returns (uint256 applicationId, string xToken);

    function save(uint256 sessionId, uint256 appId, string xToken, uint256 status, string kioskId) public;

    function getSession(uint256 index) public view returns (uint256 appId, string xToken, uint256 status);

    function setHasActiveCashIn(uint256 _sessionId, bool _flag) public;

    function isHasActiveCashIn(uint256 _sessionId) public view returns (bool);

    function getStatus(uint256 index) public view returns (uint256);

    function getStatusAndXToken(uint256 index) public view returns (uint256, string);

    function setStatus(uint256 index, uint256 status) public;

}

contract ATokenStorage {

    function set(address customer, uint256 amount) public;

    function get(address consumer) public view returns (uint256);

}