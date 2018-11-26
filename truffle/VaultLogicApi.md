* [SessionApi](#session-api)
  * [closeSession](#function-closesession)
* [KioskApi](#kiosk-api)
  * [getKiosk](#function-getkiosk)
* [CameraApi](#camera-api)
  * [scanQRCodeWithLights](#function-scanqrcodewithlights)
  * [scanQRCodeWithoutLights](#function-scanqrcodewithoutlights)
  * [stopQRScanning](#function-stopqrscanning)
* [PrinterApi](#printer-api)
  * [createReceipt](#function-createreceipt)
  * [printReceipt](#function-printreceipt)
* [CashInApi](#cashin-api)
  * [getFeePercent](#function-getcashinfeepercent)
  * [openCashInChannel](#function-opencashinchannel)
  * [closeCashInChannel](#function-closecashinchannel)
  * [balanceOf](#function-balanceof)
* [CashOutApi](#cashout-api)
  * [getCashOutFeePercent](#function-getcashoutfeepercent)
  * [openCashOutChannel](#function-opencashoutchannel)
  * [validateCashOutChannel](#function-validatecashoutchannel)
  * [closeCashOutChannel](#function-closecashoutchannel)
  * [rollbackCashOutChannel](#function-rollbackcashoutchannel)


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


---

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
function _success(uint256 _sessionId, string _port, string _url, string _href) public {
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
function _scanned(uint256 _sessionId, string _data) public {
    //handling goes here
}
```

*Fail callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |

*Example of client contract function declaration*
```solidity
function _fail(uint256 _sessionId) public {
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
function _success(uint256 _sessionId, string _port, string _url, string _href) public {
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
function _scanned(uint256 _sessionId, string _data) public {
    //handling goes here
}
```

*Fail callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | Id of the session |

*Example of client contract function declaration*
```solidity
function _fail(uint256 _sessionId) public {
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
function _success(uint256 _sessionId) public {
    //handling goes here
}
```

*Fail callback inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _sessionId | Id of the session |

*Example of client contract function declaration*
```solidity
function _fail(uint256 _sessionId) public {
    //handling goes here
}
```


---

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


---

# CashIn-api

## *function* getCashInFeePercent

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
function _fail(uint256 _sessionId) public {
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
function _success(uint256 _sessionId, uint256 _cashInId) public {
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
function _update(uint256 _sessionId, uint256 _cashInId, uint256 _amount) public {
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
function _success(uint256 _sessionId, uint256 _cashInId) public {
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
function _fail(uint256 _sessionId, uint256 _cashInId) public {
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


---

# CashOut-api

## *function* getCashOutFeePercent

*Get VaultLogic fee percent for CashOut channel*

```solidity
function getFeePercent() external view returns (uint256);
```

*Output*

| Type | Name | Description |
|-|-|-|
| *uint256* | _fee | VaultLogic fee percent for CashOutChannel |

---

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

---

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

---

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

---

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


