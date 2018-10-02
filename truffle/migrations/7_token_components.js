var ContractRegistry = artifacts.require("./ContractRegistry.sol");
var TokenLib = artifacts.require("./TokenLib.sol");
var TokenStorage = artifacts.require("./TokenStorage.sol");
var TokenManager = artifacts.require("./TokenManager.sol");

module.exports = function (deployer) {
    deployer.deploy(TokenLib);
    deployer.link(TokenLib, TokenStorage);
    deployer.deploy(TokenStorage, ContractRegistry.address);
    deployer.deploy(TokenManager, ContractRegistry.address);
};