pragma solidity 0.4.24;

interface ICashChannelsManager {
    
    function openCashInChannel(address _application, uint256 _sessionId) external returns(uint256);

    function closeCashInChannel(address _application, uint256 _sessionId, uint256 _channelId, uint256[] fees, address[] parties) external;

    function confirmOpen(uint256 channelId) external;

    function confirmClose(uint256 channelId) external;

    function updateCashInBalance(uint256 channelId, uint256 amount) external;

    function balanceOf(address _application, uint256 _channelId) external view returns(uint256);

}