pragma solidity 0.4.24;

import "./api/AKioskManager.sol";
import "../platform/Named.sol";
import "../platform/Mortal.sol";
import "../platform/Component.sol";
import "../storages/api/AKioskStorage.sol";
import "../oracles/api/AKioskOracle.sol";
import "../controllers/api/AKioskController.sol";

contract KioskManager is AKioskManager, Named("kiosk-manager"), Mortal, Component {

    string constant CONTROLLER = "kiosk-controller";
    string constant ORACLE = "kiosk-oracle";
    string constant STORAGE = "kiosk-storage";

    constructor(address _config) Component(_config) public {}

    // @formatter:off
    function getKiosk(
        address _application,
        uint256 _sessionId,
        function(
            uint256,
            string memory,
            string memory,
            string memory,
            string memory,
            uint256[] memory,
            uint256[] memory
        ) external _success,
        function(uint256) external _fail
    )
        external
    // @formatter:on
    {
        uint256 id = AKioskStorage(context.get(STORAGE)).createKioskInfoRequest(_sessionId, _success, _fail);
        AKioskOracle(context.get(ORACLE)).onNextGetKioskInfo(id, _sessionId);
    }

    // @formatter:off
    function confirmSuccessGetKioskInfo(
        uint256 _commandId,
        string _id,
        string _location,
        string _name,
        string _timezone,
        uint256[] _bills,
        uint256[] _amounts
    )
        public
    {
        (
            uint256 sessionId,
            function(uint256,string memory,string memory,string memory,string memory,uint256[] memory,uint256[] memory) external success
            ,
        ) = AKioskStorage(context.get(STORAGE)).retrieveKioskInfoRequest(_commandId);
        AKioskController(context.get(CONTROLLER)).respondSuccess(sessionId, _id, _location, _name, _timezone, _bills, _amounts, success);
    }
    // @formatter:on

    // @formatter:off
    function confirmFailGetKioskInfo(
        uint256 _commandId
    )
        public
    {
        function(uint256) external fail;
        uint256 sessionId;
        (sessionId,,fail) = AKioskStorage(context.get(STORAGE)).retrieveKioskInfoRequest(_commandId);
        AKioskController(context.get(CONTROLLER)).respondFail(sessionId, fail);
    }
    // @formatter:on

}