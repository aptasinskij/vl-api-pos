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

    function getKiosk(uint256 _sessionId) public view returns (string _id, string _location, string _name, string _timezone);

    function scanQRCodeWithLights(uint256 _sessionId) public onlyRegisteredApp returns (bool _success, string _url);

    function scanQRCode(uint256 _sessionId) public pure onlyRegisteredApp returns (bool _success, string _url);

    function stopQRScanning(uint256 _sessionId) public pure onlyRegisteredApp returns (bool _success);

    function getReceiptUrl(uint256 _sessionId) public pure onlyRegisteredApp returns (bool _success, string _id, string _url);

    function printReceipt(uint256 _sessionId, string _id, string _data) public pure onlyRegisteredApp returns (bool _success);

}
