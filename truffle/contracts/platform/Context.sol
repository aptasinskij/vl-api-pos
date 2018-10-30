pragma solidity 0.4.24;

contract Context {

    mapping(string => address) context;

    function register(string memory name) public {
        context[name] = msg.sender;
    }

    function get(string memory name) public view returns (address) {
        address component = context[name];
        require(component != 0x0, "component not found");
        return component;
    }

}
