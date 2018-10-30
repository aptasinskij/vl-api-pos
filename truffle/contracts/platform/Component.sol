pragma solidity 0.4.24;

import "./Context.sol";
import "./Named.sol";
import "./Config.sol";

contract Component is Named {

    Context internal context;
    address internal database;

    constructor(address _config) internal {
        database = Config(_config).database();
        context = Context(Config(_config).context());
        /*address deployed = context.get(name);
        if (deployed != 0x0) Mortal(deployed).kill();*/
        context.register(name);
    }

}
