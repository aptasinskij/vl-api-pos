pragma solidity 0.4.24;

contract Owned {

    address public owner;

    modifier onlyOwner {
        require(msg.sender == owner, "only owner allowed");
        _;
    }

    modifier txOriginOwner {
        require(tx.origin == owner, "only owner allowed");
        _;
    }

    constructor() internal {
        owner = msg.sender;
    }

}

contract Mortal is Owned {

    function kill() public txOriginOwner {
        selfdestruct(0x0);
    }

}

contract Named {

    string public name;

    constructor(string memory _name) internal {
        name = _name;
    }

}

contract Component is Named {

    Context internal context;
    address internal database;

    constructor(address _config) internal {
        database = Config(_config).database();
        context = Context(Config(_config).context());
        address deployed = context.get(name);
        if (deployed != 0x0) Mortal(deployed).kill();
        context.register(name);
    }

}

contract Context {

    mapping(string => address) context;

    function register(string memory name) public {
        context[name] = msg.sender;
    }

    function get(string memory name) public view returns (address) {
        return context[name];
    }

}

contract Config {

    address public context;
    address public database;

    constructor(address _context, address _database) public {
        context = _context;
        database = _database;
    }

}