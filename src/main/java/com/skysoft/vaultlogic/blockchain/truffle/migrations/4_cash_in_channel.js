var ContractRegistry = artifacts.require("./ContractRegistry.sol");
var CashInLib = artifacts.require("./CashInLib.sol");
var CashInStorage = artifacts.require("./CashInStorage.sol");
var CashAcceptorOracle = artifacts.require("./CashAcceptorOracle.sol");
var CashChannelsManager = artifacts.require("./CashChannelsManager.sol");

module.exports = function (deployer) {
  deployer.deploy(CashInLib);
  deployer.link(CashInLib, CashInStorage);
  deployer.deploy(CashInStorage, ContractRegistry.address);
  deployer.deploy(CashChannelsManager, ContractRegistry.address);
  deployer.deploy(CashAcceptorOracle, ContractRegistry.address);
};