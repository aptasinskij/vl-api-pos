var ContractRegistry = artifacts.require("./ContractRegistry.sol");
var CapitalHero = artifacts.require("./CapitalHero.sol");

module.exports = function (deployer) {
    deployer.deploy(CapitalHero, ContractRegistry.address);
};