var Config = artifacts.require("Config");
var Context = artifacts.require("Context");
var Database = artifacts.require("Database");

module.exports = function(deployer) {
    deployer.deploy(Config, Context.address, Database.address);
};