pragma solidity 0.4.24;

contract Ownable {

    address public owner;

    modifier onlyOwner() {
        require(msg.sender == owner, "Illegal access state");
        _;
    }

    constructor() public {
        owner = msg.sender;
    }

}