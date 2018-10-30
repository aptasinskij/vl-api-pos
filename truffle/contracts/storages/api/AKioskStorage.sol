pragma solidity 0.4.24;

contract AKioskStorage {

    event Saved(string _kioskId, string _location, string _name, string _timezone);

    function save(string memory _kioskId, string memory _location, string memory _name, string memory _timezone) public;

    function get(string memory _kioskId) public view returns (string memory _location, string memory _name, string memory _timezone);

}
