pragma solidity 0.4.24;

contract Owned {

    address public owner;

    modifier onlyOwner() {
        require(msg.sender == owner, "Illegal access state");
        _;
    }

    constructor() public {
        owner = msg.sender;
    }

}