var Config = artifacts.require("Config");
var CashInLib = artifacts.require("./CashInLib.sol");
var CashInStorage = artifacts.require("./CashInStorage.sol");
var CashAcceptorOracle = artifacts.require("./CashInOracle.sol");
var CashChannelsManager = artifacts.require("./CashChannelsManager.sol");
var CashInController = artifacts.require("CashInController");

module.exports = function (deployer) {
  deployer.deploy(CashInLib);
  deployer.link(CashInLib, CashChannelsManager);
  // deployer.link(CashInLib, CashInStorage);
  // deployer.deploy(CashInStorage, Config.address);
  deployer.deploy(CashChannelsManager, Config.address);
  deployer.deploy(CashAcceptorOracle, Config.address);
  deployer.deploy(CashInController, Config.address);
};