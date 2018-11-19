var Config = artifacts.require("Config");
var CashOutLib = artifacts.require("CashOutLib");
var CashOutStorage = artifacts.require("CashOutStorage");
var CashOutOracle = artifacts.require("CashOutOracle");
var CashOutManager = artifacts.require("CashOutManager");
var CashOutController = artifacts.require("CashOutController");

module.exports = function (deployer) {
    deployer.deploy(CashOutLib);
    deployer.link(CashOutLib, CashOutStorage);
    deployer.deploy(CashOutStorage, Config.address);
    deployer.deploy(CashOutManager, Config.address);
    deployer.deploy(CashOutController, Config.address);
    deployer.deploy(CashOutOracle, Config.address);
};