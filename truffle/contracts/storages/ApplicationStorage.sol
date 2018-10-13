pragma solidity 0.4.24;

import {ApplicationLib} from "../libs/Libraries.sol";
import {AnApplicationStorage} from "./Storages.sol";
import "../Platform.sol";

contract ApplicationStorage is AnApplicationStorage, Named("application-storage"), Mortal, Component {

    using ApplicationLib for address;

    constructor(address _config) Component(_config) public {}

    function createApplication(uint256 _id, string _name, address _owner, string _url, address _appAddr, uint256 _status) public {
        database.createApplication(ApplicationLib.Application({
            id: _id,
            name: _name,
            owner: _owner,
            url: _url,
            deployedAddress: _appAddr,
            status: ApplicationLib.Status(_status)
        }));
        emit ApplicationSaved(_id, _name, _owner, _url, _appAddr, _status);
    }

    function retrieveApplication(uint256 _id) public view returns (string _name, address _owner, string _url, address _appAddr, uint256 _status) {
        ApplicationLib.Application memory application = database.retrieveApplication(_id);
        _name = application.name;
        _owner = application.owner;
        _url = application.url;
        _appAddr = application.deployedAddress;
        _status = uint256(application.status);
    }

    function save(uint256 appId, string name, address owner, string url, address appAddr, uint256 status) public {
        database.save(appId, name, owner, url, appAddr, status);
        emit ApplicationSaved(appId, name, owner, url, appAddr, status);
    }

    function get(uint256 appId) public view returns (string, address, string, address, uint256) {
        return database.get(appId);
    }

    function isRegistered(address _applicationAddress) public view returns(bool) {
        return database.isRegistered(_applicationAddress);
    }

    function getApplicationName(uint256 appId) public view returns(string) {
        return database.getName(appId);
    }

    function getApplicationOwner(uint256 appId) public view returns(address) {
        return database.getOwner(appId);
    }

    function getApplicationUrl(uint256 appId) public view returns(string) {
        return database.getUrl(appId);
    }

    function setApplicationUrl(uint256 appId, string url) public {
        database.setUrl(appId, url);
        emit ApplicationUrlUpdated(appId, url);
    }

    function getApplicationAddress(uint256 appId) public view returns(address) {
        return database.getAddress(appId);
    }

    function setApplicationAddress(uint256 appId, address appAddr) public {
        database.setAddress(appId, appAddr);
        emit ApplicationAddressUpdated(appId, appAddr);
    }

    function getApplicationStatus(uint256 appId) public view returns(uint256) {
        return database.getStatus(appId);
    }

    function setApplicationStatus(uint256 appId, uint256 status) public {
        database.setStatus(appId, status);
        emit ApplicationStatusUpdated(appId, status);
    }

}