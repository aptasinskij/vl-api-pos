const Config = artifacts.require('Config');
const KioskLib = artifacts.require('KioskLib');
const KioskStorage = artifacts.require('KioskStorage');

module.exports = deployer => {
    deployer.deploy(KioskLib);
    deployer.link(KioskLib, KioskStorage);
    deployer.deploy(KioskStorage, Config.address);
};