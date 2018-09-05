var ContractRegistry = artifacts.require("./ContractRegistry.sol");
var SessionLib = artifacts.require("./SessionLib.sol");
var SessionRepository = artifacts.require("./SessionRepository.sol");
var SessionService = artifacts.require("./SessionService.sol");
var SessionOracle = artifacts.require("./SessionOracle.sol");

module.exports = function (deployer) {
    deployer.deploy(SessionLib);
    deployer.link(SessionLib, SessionRepository);
    deployer.deploy(SessionRepository, ContractRegistry.address);
    deployer.deploy(SessionService, ContractRegistry.address);
    deployer.deploy(SessionOracle, ContractRegistry.address);
};

/* Client app migration script */

/* var ContractRegistry = artifacts.require("./ContractRegistry.sol");
var CapitalHero = artifacts.require("./CapitalHero.sol");
var ApplicationService = artifacts.require("./ApplicationService.sol");

module.exports = function (deployer) {
    deployer.deploy(CapitalHero, ContractRegistry.address).then(capitalHero => {
        return ApplicationService.deployed().then(appService => {
            appService.registerApplication("CapitalHero", web3.eth.accounts[1], "http://localhost.com", capitalHero.address);
        });
    });
}; */