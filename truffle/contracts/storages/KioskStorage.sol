pragma solidity 0.4.24;

import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "./api/AKioskStorage.sol";
import "../platform/Component.sol";
import {KioskLib} from "../libs/Libraries.sol";

contract KioskStorage is AKioskStorage, Named("kiosk-storage"), Mortal, Component {

    using KioskLib for address;

    constructor(address _config) Component(_config) public {}

    function save(string memory _kioskId, string memory _location, string memory _name, string memory _timezone) public {
        database.save(_kioskId, _location, _name, _timezone);
        emit Saved(_kioskId, _location, _name, _timezone);
    }

    function get(string memory _kioskId) public view returns (string memory _location, string memory _name, string memory _timezone) {
        return database.get(_kioskId);
    }

}