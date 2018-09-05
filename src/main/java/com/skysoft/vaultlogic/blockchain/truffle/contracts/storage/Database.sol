pragma solidity 0.4.24;

import "../registry/RegistryComponent.sol";

contract Database is RegistryComponent {

    string constant DATABASE = "database";

    constructor(address regAddr) RegistryComponent(regAddr) public {}
    
    function getName() internal pure returns(string name) {
        return DATABASE;
    }

    /// UINT STORAGE
    mapping(bytes32 => uint256) uintStorage;

    function getUintValue(bytes32 _key) public view returns (uint256) {
        return uintStorage[_key];
    }

    function setUintValue(bytes32 _key, uint256 _value) public {
        uintStorage[_key] = _value;
    }

    function deleteUintValue(bytes32 _key) public {
        delete uintStorage[_key];
    }

    ///STRING STORAGE
    mapping(bytes32 => string) stringStorage;

    function getStringValue(bytes32 _key) public view returns (string) {
        return stringStorage[_key];
    }

    function setStringValue(bytes32 _key, string _value) public {
        stringStorage[_key] = _value;
    }

    function deleteStringValue(bytes32 _key) public {
        delete stringStorage[_key];
    }


    ///ADDRESS STORAGE
    mapping(bytes32 => address) addressStorage;

    function getAddressValue(bytes32 _key) public view returns (address) {
        return addressStorage[_key];
    }

    function setAddressValue(bytes32 _key, address _value) public {
        addressStorage[_key] = _value;
    }

    function deleteAddressValue(bytes32 _key) public {
        delete addressStorage[_key];
    }


    ///BYTES STORAGE
    mapping(bytes32 => bytes) bytesStorage;

    function getBytesValue(bytes32 _key) public view returns (bytes) {
        return bytesStorage[_key];
    }

    function setBytesValue(bytes32 _key, bytes _value) public {
        bytesStorage[_key] = _value;
    }

    function deleteBytesValue(bytes32 _key) public {
        delete bytesStorage[_key];
    }


    /// BYTES32 STORAGE
    mapping (bytes32 => bytes32) bytes32Storage;

    function getBytes32Value(bytes32 _key) public view returns (bytes32) {
        return bytes32Storage[_key];
    }

    function setBytes32Value(bytes32 _key, bytes32 _value) public {
        bytes32Storage[_key] = _value;
    }

    function deleteBytes32Value(bytes32 _key) public {
        delete bytes32Storage[_key];
    }


    ///BOOLEAN STORAGE
    mapping (bytes32 => bool) booleanStorage;

    function getBooleanValue(bytes32 _key) public view returns (bool) {
        return booleanStorage[_key];
    }

    function setBooleanValue(bytes32 _key, bool _value) public {
        booleanStorage[_key] = _value;
    }

    function deleteBooleanValue(bytes32 _key) public {
        delete booleanStorage[_key];
    }


    ///UINT STORAGE
    mapping(bytes32 => int) intStorage;

    function getIntValue(bytes32 _key) public view returns (int){
        return intStorage[_key];
    }

    function setIntValue(bytes32 _key, int _value) public {
        intStorage[_key] = _value;
    }

    function deleteIntValue(bytes32 _key) public {
        delete intStorage[_key];
    }

     ///SINGLE STRING ARGUMENT FUNCTION STORAGE
    mapping(bytes32 => function(string memory) external) singleStringArgFunctionStorage;

    function setSingleStringFunction(bytes32 key, function(string memory) external func) public {
        singleStringArgFunctionStorage[key] = func;
    }

    function getSingleStringFunction(bytes32 key) public view returns(function(string memory) external) {
        return singleStringArgFunctionStorage[key];
    }

    function deleteSingleStringFunction(bytes32 key) public {
        delete singleStringArgFunctionStorage[key];
    }

}
