var Config = artifacts.require("Config");
var KioskLib = artifacts.require("KioskLib");
var KioskStorage = artifacts.require("KioskStorage");
var KioskOracle = artifacts.require("KioskOracle");
var KioskManager = artifacts.require("KioskManager");
var KioskController = artifacts.require("KioskController");

module.exports = function (deployer) {
    deployer.deploy(KioskLib);
    deployer.link(KioskLib, KioskStorage);
    deployer.deploy(KioskStorage, Config.address);
    deployer.deploy(KioskManager, Config.address);
    deployer.deploy(KioskController, Config.address);
    deployer.deploy(KioskOracle, Config.address);
};