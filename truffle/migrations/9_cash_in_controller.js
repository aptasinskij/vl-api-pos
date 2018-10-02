var ContractRegistry = artifacts.require("ContractRegistry");
var CashInController = artifacts.require("CashInController");

module.exports = function (deployer) {
    deployer.deploy(CashInController, ContractRegistry.address);
};