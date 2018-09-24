pragma solidity 0.4.24;

contract AnApplicationManager {

    enum ApplicationStatus { PENDING, ENABLED, DISABLED }

    function registerApplication(uint256 appId, string name, address owner, string url, address appAddr) public;

    function enableApplication(uint256 appId) public;

}