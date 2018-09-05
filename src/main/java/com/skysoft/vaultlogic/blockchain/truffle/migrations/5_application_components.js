var ContractRegistry = artifacts.require("./ContractRegistry.sol");
var ApplicationLib = artifacts.require("./ApplicationLib.sol");
var ApplicationRepository = artifacts.require("./ApplicationRepository.sol");
var ApplicationService = artifacts.require("./ApplicationService.sol");
var ApplicationOracle = artifacts.require("./ApplicationOracle.sol");

module.exports = function (deployer) {
    deployer.deploy(ApplicationLib);
    deployer.link(ApplicationLib, ApplicationRepository);
    deployer.deploy(ApplicationRepository, ContractRegistry.address);
    deployer.deploy(ApplicationService, ContractRegistry.address);
    deployer.deploy(ApplicationOracle, ContractRegistry.address);
};