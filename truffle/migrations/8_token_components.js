var Config = artifacts.require("Config");
var TokenLib = artifacts.require("TokenLib");
var TokenStorage = artifacts.require("TokenStorage");
var TokenManager = artifacts.require("TokenManager");

module.exports = function (deployer) {
    deployer.deploy(TokenLib);
    deployer.link(TokenLib, TokenStorage);
    deployer.deploy(TokenStorage, Config.address);
    deployer.deploy(TokenManager, Config.address);
};