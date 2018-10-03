var Database = artifacts.require("./Database.sol");
var ContractRegistry = artifacts.require("./ContractRegistry.sol");

module.exports = function(deployer) {
  deployer.deploy(Database, ContractRegistry.address);
};
