pragma solidity 0.4.24;

contract Config {

    address public context;
    address public database;

    constructor(address _context, address _database) public {
        context = _context;
        database = _database;
    }

}
