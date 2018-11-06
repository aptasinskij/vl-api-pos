var Config = artifacts.require("Config");
var PrinterLib = artifacts.require("PrinterLib");
var PrinterStorage = artifacts.require("PrinterStorage");
var PrinterManager = artifacts.require("PrinterManager");
var PrinterOracle = artifacts.require("PrinterOracle");
var PrinterController = artifacts.require("PrinterController");

module.exports = function (deployer) {
    deployer.deploy(PrinterLib);
    deployer.link(PrinterLib, PrinterStorage);
    deployer.deploy(PrinterStorage, Config.address);
    deployer.deploy(PrinterController, Config.address);
    deployer.deploy(PrinterManager, Config.address);
    deployer.deploy(PrinterOracle, Config.address);
};