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