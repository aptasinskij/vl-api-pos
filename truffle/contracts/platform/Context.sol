pragma solidity 0.4.24;

contract Context {

    mapping(string => address) context;

    function register(string memory name) public {
        context[name] = msg.sender;
    }

    function get(string name) public view returns (address _component) {
        _component = context[name];
        require(_component != 0x0, "component not found");
    }

}
