pragma solidity 0.4.24;

import "./IRegistry.sol";
import {Owned} from "../Platform.sol";

///@dev base contract for all registry components
contract Component is Owned {

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

    string constant PARAMETER_STORAGE = "parameter-storage";
    string constant PARAMETER_MANAGER = "parameter-manager";

    string constant CAMERA_ORACLE = "camera-oracle";
    string constant CAMERA_MANAGER = "camera-manager";

    string constant DATABASE = "database";

    ///@param addr - address of deployed Registry instance
    ///@dev each component will be registered at instantiation time
    ///by name with returns from getName() abstract method
    constructor(address addr) internal {
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

    function _database() internal view returns (address) {
        return lookup(DATABASE);
    }

    function _applicationStorage() internal view returns(address) {
        return lookup(APPLICATION_STORAGE);
    }

    function _applicationOracle() internal view returns(address) {
        return lookup(APPLICATION_ORACLE);
    }

    /// session components
    function _sessionStorage() internal view returns(address) {
        return lookup(SESSION_STORAGE);
    }

    function _sessionManager() internal view returns(address) {
        return lookup(SESSION_MANAGER);
    }

    function _sessionOracle() internal view returns(address) {
        return lookup(SESSION_ORACLE);
    }

    /// cash-in-channel components
    function _cashInStorage() internal view returns(address) {
        return lookup(CASH_IN_STORAGE);
    }

    function _cashInOracle() internal view returns(address) {
        return lookup(CASH_IN_ORACLE);
    }

    /// cash channels manager
    function _cashChannelsManager() internal view returns(address) {
        return lookup(CASH_CHANNELS_MANAGER);
    }

    /// token components
    function _tokenStorage() internal view returns(address) {
        return lookup(TOKEN_STORAGE);
    }

    function _tokenManager() internal view returns(address) {
        return lookup(TOKEN_MANAGER);
    }

    /// parameter components
    function _parameterStorage() internal view returns(address) {
        return lookup(PARAMETER_STORAGE);
    }

    function _parameterManager() internal view returns(address) {
        return lookup(PARAMETER_MANAGER);
    }

    /// camera components
    function _cameraOracle() internal view returns (address) {
        return lookup(CAMERA_ORACLE);
    }

    function _cameraManager() internal view returns (address) {
        return lookup(CAMERA_MANAGER);
    }

}