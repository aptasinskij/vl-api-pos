pragma solidity 0.4.24;

import "./IRegistry.sol";
import "../Ownable.sol";
import "../repositories/application/IApplicationStorage.sol";
import "../oracles/IApplicationOracle.sol";
import "../repositories/session/ISessionStorage.sol";
import "../services/ISessionManager.sol";
import "../oracles/ISessionOracle.sol";
import "../repositories/cash-in/ICashInStorage.sol";
import "../oracles/ICashInOracle.sol";
import "../repositories/token/ITokenStorage.sol";
import "../services/ACashChannelsManager.sol";
import "../services/ITokenManager.sol";

///@dev base contract for all registry components
contract RegistryComponent is Ownable {

    address registry;

    string constant APPLICATION_STORAGE = "application-storage";
    string constant APPLICATION_ORACLE = "application-oracle";

    string constant SESSION_STORAGE = "session-storage";
    string constant SESSION_MANAGER = "session-manager";
    string constant SESSION_ORACLE = "session-oracle";

    string constant CASH_IN_STORAGE = "cash-in-storage";
    string constant CASH_IN_ORACLE = "cash-in-oracle";

    string constant CASH_CHANNELS_MANAGER = "cash-channels-manager";

    string constant TOKEN_STORAGE = "token-storage";
    string constant TOKEN_MANAGER = "token-manager";

    ///@param addr - address of deployed Registry instance
    ///@dev each component will be registered at instantiation time
    ///by name with returns from getName() abstract method
    constructor(address addr) public {
        require(addr != 0x0, "System state violation");
        registry = addr;
        IRegistry(registry).register(getName(), address(this));
    }

    ///@dev method will be accessed in each component to lookup for dependent components
    ///@param componentName - name of component to lookup for
    ///@return address of registered component or 0x0 if component is not registered
    function lookup(string componentName) internal view returns(address componentAddress) {
        componentAddress = IRegistry(registry).get(componentName);
        require(componentAddress != 0x0, "System state violation");
    }

    ///@dev abstract method that necessary for registration 
    /// of concrete implementation at instantiation time
    /// @return name of implementation
    function getName() internal pure returns(string name);

    function _applicationStorage() internal view returns(IApplicationStorage) {
        return IApplicationStorage(lookup(APPLICATION_STORAGE));
    }

    function _applicationOracle() internal view returns(IApplicationOracle) {
        return IApplicationOracle(lookup(APPLICATION_ORACLE));
    }

    /// session components
    function _sessionStorage() internal view returns(ISessionStorage) {
        return ISessionStorage(lookup(SESSION_STORAGE));
    }

    function _sessionManager() internal view returns(ISessionManager) {
        return ISessionManager(lookup(SESSION_MANAGER));
    }

    function _sessionOracle() internal view returns(ISessionOracle) {
        return ISessionOracle(lookup(SESSION_ORACLE));
    }

    /// cash-in-channel components
    function _cashInStorage() internal view returns(ICashInStorage) {
        return ICashInStorage(lookup(CASH_IN_STORAGE));
    }

    function _cashInOracle() internal view returns(ICashInOracle) {
        return ICashInOracle(lookup(CASH_IN_ORACLE));
    }

    /// cash channels manager
    function _cashChannelsManager() internal view returns(ACashChannelsManager) {
        return ACashChannelsManager(lookup(CASH_CHANNELS_MANAGER));
    }

    /// token components
    function _tokenStorage() internal view returns(ITokenStorage) {
        return ITokenStorage(lookup(TOKEN_STORAGE));
    }

    function _tokenManager() internal view returns(ITokenManager) {
        return ITokenManager(lookup(TOKEN_MANAGER));
    }

}