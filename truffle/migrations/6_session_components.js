var ContractRegistry = artifacts.require("./ContractRegistry.sol");
var SessionLib = artifacts.require("./SessionLib.sol");
var SessionStorage = artifacts.require("./SessionStorage.sol");
var SessionManager = artifacts.require("./SessionManager.sol");
var SessionOracle = artifacts.require("./SessionOracle.sol");

module.exports = function (deployer) {
    deployer.deploy(SessionLib);
    deployer.link(SessionLib, SessionStorage);
    deployer.link(SessionLib, SessionManager);
    deployer.deploy(SessionStorage, ContractRegistry.address);
    deployer.deploy(SessionManager, ContractRegistry.address);
    deployer.deploy(SessionOracle, ContractRegistry.address);
};