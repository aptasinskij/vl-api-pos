* [CameraApi](#camera-api)
  * [scanQRCodeWithLights](#function-scanqrcodewithlights)
  * [scanQRCodeWithoutLights](#function-scanqrcodewithoutlights)
  * [stopQRScanning](#function-stopqrscanning)
* [CashInApi](#cashin-api)

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
| *uint256* | session id | id of the session in which camera was enabled |
| *string memory* | port | port in which client can bind to retrieve live streaming video |
| *string memory* | url | url in which client can bind to retrieve live streaming video |
| *string memory* | href | combination of url and port params |

*Example of client contract function declaration*
```solidity
function __success(uint256 _sessionId, string _port, string _url, string _href) public {
    //handling goes here
}
```

*Scanned callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | id of the session in which QR code was scanned |
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
| *uint256* | session id | id of the session |

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
| *uint256* | session id | id of the session in which camera was enabled |
| *string memory* | port | port in which client can bind to retrieve live streaming video |
| *string memory* | url | url in which client can bind to retrieve live streaming video |
| *string memory* | href | combination of url and port params |

*Example of client contract function declaration*
```solidity
function __success(uint256 _sessionId, string _port, string _url, string _href) public {
    //handling goes here
}
```

*Scanned callback inputs*

| Type | Stands for | Description |
|-|-|-|
| *uint256* | session id | id of the session in which QR code was scanned |
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
| *uint256* | session id | id of the session |

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
| *uint256* | _sessionId | id of the session |

*Example of client contract function declaration*
```solidity
function __success(uint256 _sessionId) public {
    //handling goes here
}
```

*Fail callback inputs*

| Type | Name | Description |
|-|-|-|
| *uint256* | _sessionId | id of the session |

*Example of client contract function declaration*
```solidity
function __fail(uint256 _sessionId) public {
    //handling goes here
}
```

---

# CashIn-api