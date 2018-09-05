var ContractRegistry = artifacts.require("./ContractRegistry.sol");

module.exports = function(deployer) {
  deployer.deploy(ContractRegistry);
};
