pragma solidity 0.4.24;

contract ASessionManager {

    function createSession(uint256 _sessionId, uint256 _appId, string _xToken, string _kioskId) public;

    function closeSession(uint256 sessionId) public;

    function confirmClose(uint256 sessionId) public;

    function isActive(uint256 sessionId) public view returns(bool);

    function isHasActiveCashIn(uint256 _sessionId) public view returns(bool);

    function activate(uint256 _sessionId) public;

    function validateCanOpenCashIn(uint256 _sessionId, address _application) public view returns (bool _canOpenCashIn);

    function setSessionHasActiveCashIn(uint256 _sessionId) public;

    function setSessionIsNotHasActiveCashIn(uint256 _sessionId) public;

}
