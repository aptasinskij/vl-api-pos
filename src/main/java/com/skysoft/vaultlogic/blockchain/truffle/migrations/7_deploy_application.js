var ContractRegistry = artifacts.require("./ContractRegistry.sol");
var CapitalHero = artifacts.require("./CapitalHero.sol");

module.exports = function (deployer) {
    deployer.deploy(CapitalHero, ContractRegistry.address);
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