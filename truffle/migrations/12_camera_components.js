const Config = artifacts.require("Config");
const SessionLib = artifacts.require("CameraLib");
const ApplicationLib = artifacts.require("ApplicationLib");
const CameraLib = artifacts.require("CameraLib");
const CameraManager = artifacts.require("CameraManager");
const CameraOracle = artifacts.require("CameraOracle");
const CameraController = artifacts.require("CameraController");

module.exports = function (deployer) {
    deployer.link(ApplicationLib, CameraController);
    deployer.deploy(CameraController, Config.address);

    deployer.deploy(CameraLib);
    deployer.link(SessionLib, CameraManager);
    deployer.link(CameraLib, CameraManager);
    deployer.deploy(CameraManager, Config.address);

    deployer.link(CameraLib, CameraOracle);
    deployer.deploy(CameraOracle, Config.address);
};