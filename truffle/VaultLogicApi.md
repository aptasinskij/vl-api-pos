* [CameraApi](#camera-api)
  * [scanQRCodeWithLights](#function-scanqrcodewithlights)
  * [scanQRCodeWithoutLights](#function-scanqrcodewithoutlights)
  * [stopQRScanning](#function-stopqrscanning)
* [CashInApi](#cashin-api)
  * [getFeePercent](#function-getfeepercent)
  * [openCashInChannel](#function-opencashinchannel)
  * [closeCashInChannel](#function-closecashinchannel)
  * [balanceOf](#function-balanceof)
* [KioskApi](#kiosk-api)

# Camera-api

## *function* scanQRCodeWithLights

**Enables kiosk camera in QR scanning mode with enabled lights**

```solidity
function scanQRCodeWithLights(
    uint256 _sessionId,
    function(uint256, string memory, string memory, string memory) external _success,
    function(uint256, string memory) external _scanned,
    function(uint256) external _fail
)
    external;
```

> Developer related information

*Inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _sessionId | id of the session |
| *function(uint256, string memory, string memory, string memory) external* | _success | camera successfully enabled callback |
| *function(uint256, string memory) external* | _scanned | QR code scanned callback |
| *function(uint256) external* | _fail | fail to enable camera callback |

*Success callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session in which camera was enabled |
| *string memory* | port | Port in which client can bind to retrieve live streaming video |
| *string memory* | url | Url in which client can bind to retrieve live streaming video |
| *string memory* | href | Combination of url and port params |

*Example of client contract function declaration*
```solidity
function __success(uint256 _sessionId, string _port, string _url, string _href) public {
    //handling goes here
}
```

*Scanned callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session in which QR code was scanned |
| *string memory* | QR code data | Scanned QR code data |

*Example of client contract function declaration*
```solidity
function __scanned(uint256 _sessionId, string _data) public {
    //handling goes here
}
```

*Fail callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |

*Example of client contract function declaration*
```solidity
function __fail(uint256 _sessionId) public {
    //handling goes here
}
```

---

## *function* scanQRCodeWithoutLights

**Enables kiosk camera in QR scanning mode with disabled lights**

```solidity
function scanQRCodeWithoutLights(
    uint256 _sessionId,
    function(uint256, string memory, string memory, string memory) external _success,
    function(uint256, string memory) external _scanned,
    function(uint256) external _fail
)
    external;
```

> Developer related information

*Inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _sessionId | id of the session |
| *function(uint256, string memory, string memory, string memory) external* | _success | camera successfully enabled callback |
| *function(uint256, string memory) external* | _scanned | QR code scanned callback |
| *function(uint256) external* | _fail | fail to enable camera callback |

*Success callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session in which camera was enabled |
| *string memory* | port | Port in which client can bind to retrieve live streaming video |
| *string memory* | url | Url in which client can bind to retrieve live streaming video |
| *string memory* | href | Combination of url and port params |

*Example of client contract function declaration*
```solidity
function __success(uint256 _sessionId, string _port, string _url, string _href) public {
    //handling goes here
}
```

*Scanned callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session in which QR code was scanned |
| *string memory* | QR code data | scanned QR code data |

*Example of client contract function declaration*
```solidity
function __scanned(uint256 _sessionId, string _data) public {
    //handling goes here
}
```

*Fail callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |

*Example of client contract function declaration*
```solidity
function __fail(uint256 _sessionId) public {
    //handling goes here
}
```

---

## *function* stopQRScanning

**Disables kiosk camera**

```solidity
function stopQRScanning(
    uint256 _sessionId,
    function(uint256) external _success,
    function(uint256) external _fail
)
    external;
```

*Inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _sessionId | id of the session |
| *function(uint256) external* | _success | camera successfully disabled callback |
| *function(uint256) external* | _fail | fail to disable camera callback |

*Success callback inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _sessionId | Id of the session |

*Example of client contract function declaration*
```solidity
function __success(uint256 _sessionId) public {
    //handling goes here
}
```

*Fail callback inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _sessionId | Id of the session |

*Example of client contract function declaration*
```solidity
function __fail(uint256 _sessionId) public {
    //handling goes here
}
```


---

# CashIn-api

## *function* getFeePercent

*Get VaultLogic fee percent for CashIn channel*

```solidity
function getFeePercent() external view returns (uint256);
```

*Output*

| Type | Name | Description |
|-|-|-|
| *uint256* | _fee | VaultLogic fee percent for CashInChannel |

---

## *function* openCashInChannel

*Create CashIn channel*

> Require that the session is active and has no active CashIn channel.
  CashIn channel will be assigned status CREATING 
  and will proceed either to ACTIVE or FAILED_TO_CREATE after successful or fail confirmation respectively

```solidity
function openCashInChannel(
    uint256 _sessionId,
    uint256 _maxBalance,
    function(uint256) external _fail,
    function(uint256, uint256) external _success,
    function(uint256, uint256, uint256) external _update
)
    external;
```

*Input*

| Type | Name | Description |
|-|-|-|
| *uint256* | _sessionId | id of the session in which CashIn channel is creating |
| *uint256* | _maxBalance | max balance of the CashIn channel |
| *function(uint256) external* | _fail | fail to open CashIn channel callback function  |
| *function(uint256, uint256) external* | _success | success open CashIn channel callback function |
| *function(uint256, uint256, uint256) external* | _update | CashIn channel balance update callback function |

*Fail callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |

*Example of client contract function declaration*
```solidity
function __fail(uint256 _sessionId) public {
    //handling
}
```

*Success callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |
| *uint256* | CashIn id | Id of the created CashIn channel |

*Example of client contract function declaration*

```solidity
function __success(uint256 _sessionId, uint256 _cashInId) public {
    //handling
}
```

*Update callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |
| *uint256* | CashIn id | Id of the created CashIn channel |
| *uint256* | Update amount | Amount of income tokens |

*Example of client contract function declaration*

```solidity
function __update(uint256 _sessionId, uint256 _cashInId, uint256 _amount) public {
    //handling
}
```

---

## *function* closeCashInChannel

*Close CashIn channel*

> Require that the session is active and CashIn channel in ACTIVE status
  CashIn channel with be assigned status CLOSE_REQUESTED 
  and will proceed either to CLOSED or FAILED_TO_CLOSE after successful or fail confirmation respectively
  
```solidity
function closeCashInChannel(
    uint256 _sessionId,
    uint256 _channelId,
    uint256[] _fees,
    address[] _parties,
    function(uint256, uint256) external _success,
    function(uint256, uint256) external _fail
)
    external;
```

*Inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _sessionId | id of the session in which CashIn channel is closed |
| *uint256* | _channelId | id of the channel to close |
| *uint256[]* | _fees | array of fee amount per split definition party |
| *address[]* | _parties | array of parties of split definition |
| *function(uint256, uint256) external* | _success | success close CashIn channel callback function |
| *function(uint256, uint256) external* | _fail | fail to close CashIn channel callback function |

*Success callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |
| *uint256* | CashIn id | Id of the closed CashIn channel |

*Example of client contract function declaration*
```solidity
function __success(uint256 _sessionId, uint256 _cashInId) public {
    //handling
}
```

*Fail callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |
| *uint256* | CashIn id | Id of the CashIn channel |

*Example of client contract function declaration*
```solidity
function __fail(uint256 _sessionId, uint256 _cashInId) public {
    //handling
}
```

---

## *function* balanceOf

*Get balance of the channel*

```solidity
function balanceOf(
    uint256 _channelId
)
    external
    view
    returns (
        uint256 _balance
    );
```

*Inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _channelId | id of the CashIn channel |

*Outputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _balance | balance of the CashIn channel |
