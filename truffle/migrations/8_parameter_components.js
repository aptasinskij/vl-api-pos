var ContractRegistry = artifacts.require("./ContractRegistry.sol");
var ParameterLib = artifacts.require("./ParameterLib.sol");
var ParameterStorage = artifacts.require("./ParameterStorage.sol");
var ParameterManager = artifacts.require("./ParameterManager.sol");

module.exports = function (deployer) {
    deployer.deploy(ParameterLib);
    deployer.link(ParameterLib, ParameterStorage);
    deployer.deploy(ParameterStorage, ContractRegistry.address);
    deployer.deploy(ParameterManager, ContractRegistry.address);
};