* [PrinterApi](#printer-api)
  * [getKiosk](#*function*-createreceipt)
  * [getKiosk](#*function*-printreceipt)

# Printer-api

## *function* createReceipt

*Create receipt*

```solidity
function createReceipt(
    uint256 _sessionId,
    function(uint256, string memory, string memory) external _success,
    function(uint256) external _fail
)
    public;
```

*Inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | session id | id of the session in which receipt must create |
| *function(uint256, string memory, string memory) external* | _success | success creating receipt callback function |
| *function(uint256) external* | _fail | fail to create receipt callback function |

*Success callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | command id | Id of the session |
| *string* | receipt id | Id of the created receipt |
| *string* | url | Url of the created receipt  |

*Example of client contract function declaration*
```solidity
function _success(uint256 _commandId, string _receiptId, string _url) public {
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

---

## *function* printReceipt

*Print receipt*

```solidity
function printReceipt(
         uint256 _sessionId,
         string _receiptId,
         string _data,
         bytes32[] _paramNames,
         bytes32[] _paramValues,
         function(uint256) external _success,
         function(uint256) external _fail
     )
         public;
```

*Inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | session id | id of the session in which receipt must be printed |
| *string* | receipt id | id of the receipt |
| *string* | data | information which mast be printed on receipt |
| *bytes32[]* | param names | array of parameters names from application |
| *bytes32[]* | param values | array of values from application |
| *function(uint256) external* | _success | success printing receipt callback function |
| *function(uint256) external* | _fail | fail to print receipt callback function |

*Success callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | command id | Id of the session |

*Example of client contract function declaration*
```solidity
function _success(uint256 _commandId) public {
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