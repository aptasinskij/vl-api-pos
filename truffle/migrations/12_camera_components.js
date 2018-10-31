const Config = artifacts.require("Config");
const CameraLib = artifacts.require("CameraLib");
const CameraStorage = artifacts.require("CameraStorage");
const CameraManager = artifacts.require("CameraManager");
const CameraOracle = artifacts.require("CameraOracle");
const CameraController = artifacts.require("CameraController");

module.exports = function (deployer) {
    deployer.deploy(CameraLib);
    deployer.link(CameraLib, CameraStorage);
    deployer.deploy(CameraStorage, Config.address);
    deployer.deploy(CameraController, Config.address);
    deployer.deploy(CameraManager, Config.address);
    deployer.deploy(CameraOracle, Config.address);
};