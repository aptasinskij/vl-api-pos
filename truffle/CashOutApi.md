* [CashOutApi](#cashout-api)
  * [getFeePercent](#function-getfeepercent)
  * [openCashOutChannel](#*function*-opencashoutchannel)
  * [validateCashOutChannel](#*function*-validatecashoutchannel)
  * [closeCashOutChannel](#*function*-closecashoutchannel)
  * [rollbackCashOutChannel](#*function*-rollbackcashoutchannel)

---
# CashOut-api

## *function* getFeePercent

*Get VaultLogic fee percent for CashOut channel*

```solidity
function getFeePercent() external view returns (uint256);
```

*Output*

| Type | Name | Description |
|-|-|-|
| *uint256* | _fee | VaultLogic fee percent for CashOutChannel |


## *function* openCashOutChannel

*Create CashOut channel*

> CashOut channel will be assigned CREATING status;
  will proceed to either ACTIVE or FAILED_TO_CREATE after successful or fail confirmation respectively

```solidity
function openCashOutChannel(
    string _requestId,
    string _kioskId,
    uint256 _toWithdraw,
    uint256[] _fees,
    address[] _parties,
    function(string memory, string memory) external _fail,
    function(string memory, string memory, uint256, uint256) external _success
)
    public;
```

*Input*

| Type | Name | Description |
|-|-|-|
| *string* | _requestId | application assigned request id |
| *string* | _kioskId | id of the kiosk in which CashOut channel creating |
| *uint256* | _toWithdraw | the amount of tokens application wants to dispense |
| *uint256[]* | _fees | array of fee amount per split definition party |
| *address[]* | _parties | array of parties of split definition |
| *function(string memory, string memory) external* | _fail | fail to open CashOut channel callback function  |
| *function(string memory, string memory, uint256, uint256) external* | _success | success open CashOut channel callback function |

*Fail callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *string* | request id | Id of the request |
| *string* | kiosk id | Id of the kiosk |

*Example of client contract function declaration*
```solidity
function _fail(string _requestId, string _kioskId) public {
    //handling
}
```

*Success callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *string* | request id | Id of the session |
| *string* | kiosk id | Id of the created CashIn channel |
| *uint256* | to withdraw amount | Id of the created CashIn channel |
| *uint256* | VaultLogic fee | Id of the created CashIn channel |

*Example of client contract function declaration*

```solidity
function _success(string _requestId, string _kioskId, uint256 _toWithdraw, uint256 _vlFee) public {
    //handling
}
```

## *function* validateCashOutChannel

*Validate CashOut channel*

> Require CashOut in ACTIVE, NOT_ABLE_TO_CLOSE or ABLE_TO_CLOSE status
  CashOut channel will be assigned VALIDATING status;
  will proceed to either ABLE_TO_CLOSE or NOT_ABLE_TO_CLOSE after successful or fail confirmation respectively
  
```solidity
function validateCashOutChannel(
    uint256 _sessionId,
    uint256 _cashOutId,
    function(uint256, uint256) external _fail,
    function(uint256, uint256) external _success
)
    public;
```

*Inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _sessionId | id of the session in which CashOut channel is validating |
| *uint256* | _cashOutId | id of the CashOut channel |
| *function(uint256, uint256) external* | _success | success validate CashOut channel callback function |
| *function(uint256, uint256) external* | _fail | fail to validate CashOut channel callback function |

*Success callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |
| *uint256* | cashOut id | Id of the validated CashOut channel |

*Example of client contract function declaration*
```solidity
function _success(uint256 _sessionId, uint256 _cashOutId) public {
    //handling
}
```

*Fail callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |
| *uint256* | cashOut id | Id of the CashOut channel |

*Example of client contract function declaration*
```solidity
function _fail(uint256 _sessionId, uint256 _cashOutId) public {
    //handling
}
```

## *function* closeCashOutChannel

*Close CashOut channel*

> Require CashOut in ABLE_TO_CLOSE status and validation was in the same session
  CashOut channel will be assigned CLOSE_REQUESTED status;
  will proceed either to CLOSED or FAILED_TO_CLOSE after successful of fail confirmation respectively
  
```solidity
function closeCashOutChannel(
    uint256 _sessionId,
    uint256 _cashOutId,
    function(uint256, uint256) external _fail,
    function(uint256, uint256) external _success
)
    public;
```

*Inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _sessionId | id of the session in which CashOut channel is closing |
| *uint256* | _cashOutId | id of the CashOut channel |
| *function(uint256, uint256) external* | _success | success close CashOut channel callback function |
| *function(uint256, uint256) external* | _fail | fail to close CashOut channel callback function |

*Success callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |
| *uint256* | cashOut id | Id of the closed CashOut channel |

*Example of client contract function declaration*
```solidity
function _success(uint256 _sessionId, uint256 _cashOutId) public {
    //handling
}
```

*Fail callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |
| *uint256* | cashOut id | Id of the CashOut channel |

*Example of client contract function declaration*
```solidity
function _fail(uint256 _sessionId, uint256 _cashOutId) public {
    //handling
}
```

## *function* rollbackCashOutChannel

*Rollback CashOut channel*

> Require CashOut in ACTIVE, ABLE_TO_CLOSE or NOT_ABLE_TO_CLOSE status
  CashOut channel will be assigned CANCELED status
  
```solidity
function rollbackCashOutChannel(
    uint256 _cashOutId,
    function(uint256) external _fail,
    function(uint256) external _success
)
    public;
```

*Inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _cashOutId | id of the CashOut channel |
| *function(uint256) external* | _fail | fail to rollback CashOut channel callback function |
| *function(uint256) external* | _success | success rollback CashOut channel callback function |

*Success callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | cashOut id | Id of the CashOut channel |

*Example of client contract function declaration*
```solidity
function _success(uint256 _cashOutId) public {
    //handling
}
```

*Fail callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | cashOut id | Id of CashOut channel |

*Example of client contract function declaration*
```solidity
function _fail(uint256 _cashOutId) public {
    //handling
}
```