var ContractRegistry = artifacts.require("./ContractRegistry.sol");
var ApplicationLib = artifacts.require("./ApplicationLib.sol");
var ApplicationStorage = artifacts.require("./ApplicationStorage.sol");
var ApplicationManager = artifacts.require("./ApplicationManager.sol");
var ApplicationOracle = artifacts.require("./ApplicationOracle.sol");

module.exports = function (deployer) {
    deployer.deploy(ApplicationLib);
    deployer.link(ApplicationLib, ApplicationStorage);
    deployer.deploy(ApplicationStorage, ContractRegistry.address);
    deployer.deploy(ApplicationManager, ContractRegistry.address);
    deployer.deploy(ApplicationOracle, ContractRegistry.address);
};