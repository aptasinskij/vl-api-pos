const Config = artifacts.require("Config");
const SessionLib = artifacts.require("SessionLib");
const ApplicationLib = artifacts.require("ApplicationLib");
const PrinterLib = artifacts.require("PrinterLib");
const PrinterManager = artifacts.require("PrinterManager");
const PrinterOracle = artifacts.require("PrinterOracle");
const PrinterController = artifacts.require("PrinterController");

module.exports = function (deployer) {
    deployer.link(ApplicationLib, PrinterController);
    deployer.deploy(PrinterController, Config.address);

    deployer.deploy(PrinterLib);
    deployer.link(PrinterLib, PrinterManager);
    deployer.deploy(PrinterManager, Config.address);

    deployer.link(PrinterLib, PrinterOracle);
    deployer.deploy(PrinterOracle, Config.address);
};