var Context = artifacts.require("Context");
var CapitalHero = artifacts.require("CapitalHero");

module.exports = function (deployer) {
    deployer.deploy(CapitalHero, Context.address);
};