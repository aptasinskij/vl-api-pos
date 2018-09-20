pragma solidity 0.4.24;

import "./IRegistry.sol";
import "../Ownable.sol";

///@dev base contract for all registry components
contract RegistryComponent is Ownable {

    address registry;

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

}