var Config = artifacts.require("Config");
var CashInLib = artifacts.require("CashInLib");
var CashInStorage = artifacts.require("CashInStorage");
var CashInOracle = artifacts.require("CashInOracle");
var CashInManager = artifacts.require("CashInManager");
var CashInController = artifacts.require("CashInController");

module.exports = function (deployer) {
    deployer.deploy(CashInLib);
    deployer.link(CashInLib, CashInStorage);
    deployer.deploy(CashInStorage, Config.address);
    deployer.deploy(CashInManager, Config.address);
    deployer.deploy(CashInController, Config.address);
    deployer.deploy(CashInOracle, Config.address);
};