var ContractRegistry = artifacts.require("./ContractRegistry.sol");
var CashInLib = artifacts.require("./CashInLib.sol");
var CashInRepository = artifacts.require("./CashInRepository.sol");
var CashAcceptorOracle = artifacts.require("./CashAcceptorOracle.sol");
var CashChannelsService = artifacts.require("./CashChannelsService.sol");

module.exports = function (deployer) {
  deployer.deploy(CashInLib);
  deployer.link(CashInLib, CashInRepository);
  deployer.deploy(CashInRepository, ContractRegistry.address);
  deployer.deploy(CashChannelsService, ContractRegistry.address);
  deployer.deploy(CashAcceptorOracle, ContractRegistry.address);
};