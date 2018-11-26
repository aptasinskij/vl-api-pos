* [SessionApi](#session-api)
  * [closeSession](#function-closesession)

---
# Session-api

## *function* closeSession

*Close Session*

```solidity
function closeSession(
    uint256 _sessionId,
    function(uint256) external _success,
    function(uint256) external _fail
)
    external;
```

*Inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _sessionId | id of the session to close |
| *function(uint256) external* | _success | success close session callback function |
| *function(uint256) external* | _fail | fail to close session callback function |

*Success callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |

*Example of client contract function declaration*
```solidity
function _success(uint256 _sessionId) public {
    //handling
}
```

*Fail callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |

*Example of client contract function declaration*
```solidity
function _fail(uint256 _sessionId) public {
    //handling
}
```