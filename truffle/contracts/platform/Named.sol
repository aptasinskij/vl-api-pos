pragma solidity 0.4.24;

contract Named {

    string public name;

    constructor(string memory _name) internal {
        name = _name;
    }

}
