* [KioskApi](#kiosk-api)
  * [getKiosk](#function-getkiosk)

---
# Kiosk-api

## *function* getKiosk

*Get kiosk info*

```solidity
function getKiosk(
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
    external;
```

*Inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _sessionId | id of the session in which user wants to get info about kiosk |
| *function(uint256, string memory, string memory, string memory, string memory, uint256[] memory, uint256[] memory) external* | _success | success getting kiosk info callback function |
| *function(uint256) external* | _fail | fail to get kiosk info callback function |

*Success callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | command id | Id of the session |
| *string* | id | Id of kiosk |
| *string* | location | Location of the kiosk |
| *string* | name | Name of the kiosk |
| *string* | timezone | Kiosk timezone |
| *uint256[]* | bills | Array of available bills |
| *uint256[]* | amounts | Array of available amount of money per bills |

*Example of client contract function declaration*
```solidity
function _success(uint256 _sessionId, uint256 _cashOutId) public {
    //handling
}
```

*Fail callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | command id | Id of the session |

*Example of client contract function declaration*
```solidity
function _fail(uint256 _commandId) public {
    //handling
}
```