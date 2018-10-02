pragma solidity 0.4.24;

import "../registry/Component.sol";
import "../application/IApplication.sol";

contract AbstractController is Component {

    modifier onlyRegisteredApp {
        require(_applicationStorage().isRegistered(msg.sender), "Illegal access");
        _;
    }

    constructor(address registry) Component(registry) internal {}

}

contract ACashInController is AbstractController {

    constructor(address registry) AbstractController(registry) internal {}

    function open(uint256 _sessionId) public returns (uint256);

    function close(uint256 _sessionId, uint256 _channelId, uint256[] _fees, address[] _parties) public returns (bool);

    function balanceOf(uint256 _channelId) public view returns (uint256);

}

//TODO redesign in future
contract ASessionController is AbstractController {

    constructor(address registry) AbstractController(registry) internal {}

    function getKiosk(uint256 _sessionId) public view returns (string memory _id, string memory _location, string memory _name, string memory _timezone);

    function scanQRCodeWithLights(uint256 _sessionId) public view returns (bool _success, string memory _url);

    function scanQRCode(uint256 _sessionId) public view returns (bool _success, string memory _url);

    function stopQRScanning(uint256 _sessionId) public view returns (bool _success);

    function getReceiptUrl(uint256 _sessionId) public view returns (bool _success, string memory _id, string memory _url);

    function printReceipt(uint256 _sessionId, string _id, string _data) public view returns (bool _success);

}