pragma solidity 0.4.24;

import "../registry/Component.sol";
import {KioskLib} from "../libs/Libraries.sol";
import {AKioskStorage} from "./Storages.sol";

contract KioskStorage is Component, AKioskStorage {

    string constant COMPONENT_NAME = "kiosk-storage";
    string constant DATABASE = "database";

    using KioskLib for address;

    constructor(address registryAddr) Component(registryAddr) public {}

    function getName() internal pure returns (string) {
        return COMPONENT_NAME;
    }

    function save(string memory _kioskId, string memory _location, string memory _name, string memory _timezone) public {
        lookup(DATABASE).save(_kioskId, _location, _name, _timezone);
        emit Saved(_kioskId, _location, _name, _timezone);
    }

    function get(string _kioskId) public view returns (string memory _location, string memory _name, string memory _timezone) {
        return lookup(DATABASE).get(_kioskId);
    }

}