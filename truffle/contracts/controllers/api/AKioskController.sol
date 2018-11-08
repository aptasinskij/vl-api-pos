pragma solidity 0.4.24;

import "./public/KioskApi.sol";

contract AKioskController is KioskApi {

    constructor() internal {}

    // @formatter:off
    function respondSuccess(
        uint256 _sessionId,
        string _id,
        string _location,
        string _name,
        string _timezone,
        uint256[] _bills,
        uint256[] _amounts,
        function(
            uint256,
            string memory,
            string memory,
            string memory,
            string memory,
            uint256[] memory,
            uint256[] memory
        )
        external _callback
    )
        public
    {
        _callback(_sessionId, _id, _location, _name, _timezone, _bills, _amounts);
    }

    function respondFail(
        uint256 _sessionId,
        function(uint256) external _callback
    )
        public
    {
        _callback(_sessionId);
    }
    // @formatter:on

}
