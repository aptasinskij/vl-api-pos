var Config = artifacts.require("Config");
var ParameterLib = artifacts.require("ParameterLib");
var ParameterStorage = artifacts.require("ParameterStorage");
var ParameterManager = artifacts.require("ParameterManager");

module.exports = function (deployer) {
    deployer.deploy(ParameterLib);
    deployer.link(ParameterLib, ParameterStorage);
    deployer.deploy(ParameterStorage, Config.address);
    deployer.deploy(ParameterManager, Config.address);
};