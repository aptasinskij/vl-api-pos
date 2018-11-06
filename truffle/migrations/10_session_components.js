var Config = artifacts.require("Config");
var SessionLib = artifacts.require("SessionLib");
var SessionStorage = artifacts.require("SessionStorage");
var SessionManager = artifacts.require("SessionManager");
var SessionOracle = artifacts.require("SessionOracle");
var SessionController = artifacts.require("SessionController");

module.exports = function (deployer) {
    deployer.deploy(SessionLib);
    deployer.link(SessionLib, SessionStorage);
    deployer.deploy(SessionController, Config.address);
    deployer.deploy(SessionStorage, Config.address);
    deployer.deploy(SessionManager, Config.address);
    deployer.deploy(SessionOracle, Config.address);
};