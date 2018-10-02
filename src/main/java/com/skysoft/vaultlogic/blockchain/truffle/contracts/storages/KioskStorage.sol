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

    function createKiosk(string _id, string _location, string _name, string _timezone) public {
        KioskLib.Kiosk memory kiosk = KioskLib.Kiosk(_id, _location, _name, _timezone);
        require(lookup(DATABASE).createKiosk(kiosk), "Kiosk already exists");
    }

    function retrieveKiosk(string _id) public view returns (string memory _location, string memory _name, string memory _timezone) {
        KioskLib.Kiosk memory kiosk = lookup(DATABASE).retrieveKiosk(_id);
        return (kiosk.location, kiosk.name, kiosk.timezone);
    }

}