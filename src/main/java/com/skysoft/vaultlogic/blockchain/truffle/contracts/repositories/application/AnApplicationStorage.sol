pragma solidity 0.4.24;

contract AnApplicationStorage {
    
    function save(uint256 appId, string name, address owner, string url, address appAddr, uint256 status) public;

    function get(uint256 appId) public view returns (string, address, string, address, uint256);

    function isRegistered(address _applicationAddress) public view returns(bool);

    function getApplicationName(uint256 appId) public view returns(string);

    function getApplicationOwner(uint256 appId) public view returns(address);

    function getApplicationUrl(uint256 appId) public view returns(string);

    function setApplicationUrl(uint256 appId, string url) public;

    function getApplicationAddress(uint256 appId) public view returns(address);

    function setApplicationAddress(uint256 appId, address appAddr) public;

    function getApplicationStatus(uint256 appId) public view returns(uint256);

    function setApplicationStatus(uint256 appId, uint256 status) public;

}