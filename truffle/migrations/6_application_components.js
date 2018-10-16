var Config = artifacts.require("Config");
var ApplicationLib = artifacts.require("ApplicationLib");
var ApplicationStorage = artifacts.require("ApplicationStorage");
var ApplicationManager = artifacts.require("ApplicationManager");
var ApplicationOracle = artifacts.require("ApplicationOracle");

module.exports = function (deployer) {
    deployer.deploy(ApplicationLib);
    deployer.link(ApplicationLib, ApplicationStorage);
    deployer.deploy(ApplicationStorage, Config.address);
    deployer.deploy(ApplicationManager, Config.address);
    deployer.deploy(ApplicationOracle, Config.address);
};