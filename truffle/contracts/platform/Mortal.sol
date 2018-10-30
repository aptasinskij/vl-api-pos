pragma solidity 0.4.24;

import "./Owned.sol";

contract Mortal is Owned {

    function kill() public txOriginOwner {
        selfdestruct(0x0);
    }

}
