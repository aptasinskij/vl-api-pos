var Database = artifacts.require("Database");

module.exports = function(deployer) {
  deployer.deploy(Database);
};
