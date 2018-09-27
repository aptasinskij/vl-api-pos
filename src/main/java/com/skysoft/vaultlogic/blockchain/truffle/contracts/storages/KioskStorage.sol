pragma solidity 0.4.24;

import "../registry/Component.sol";
import {KioskLib} from "../libs/Libraries.sol";
import {AKioskStorage} from "./Storages.sol";

contract KioskStorage is Component, AKioskStorage {

    string constant COMPONENT_NAME = "kiosk-storage";
    string constant DATABASE = "database";

    using KioskLib for address;

    constructor(address registryAddr) Component(registryAddr) public {}

    function getName() internal pure returns(string) {
        return COMPONENT_NAME;
    }

    function saveKiosk(string shortId, string locationAddress, string name, string timeZone) public {
        lookup(DATABASE).save(shortId, locationAddress, name, timeZone);
    }

    function retrieveKiosk(string shortId) public view
    returns(
        string locationAddress,
        string name,
        string timeZone
    ) {
        return lookup(DATABASE).retrieve(shortId);
    }

}