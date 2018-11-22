* [CashInApi](#cashinapi)
  * [getFeePercent](#function-get)
  * [openCashInChannel](#function-opencashinchannel)
  * [closeCashInChannel](#function-closecashinchannel)
  * [balanceOf](#function-balanceof)

# CashInApi


## *function* get

CashInApi.getFeePercent() `nonpayable` `138cea01`

**Get VaultLogic fee percent for CashIn channel
**

> 

Inputs

| **type** | **name** | **description** |
|-|-|-|

Outputs

| | | |
|-|-|-|
| *uint256* | _fee | - the VaultLogic fee percent
 |


## *function* openCashInChannel

CashInApi.openCashInChannel(_sessionId, _maxBalance,  _fail, _success, _update) `nonpayable` `41ad3c6f`

**Create CashIn channel. Require that the session does not has active CashIn channel.
** 

> CashIn channel will be assigned status CREATING; will proceed either to ACTIVE or FAILED_TO_CREATE after successful or fail confirmation respectively

Inputs

| **type** | **name** | **description** |
|-|-|-|
| *uint256* | _sessionId | - id of the session in which CashIn channel is creating
 |
| *uint256* | _maxBalance | - max balance of the CashIn channel
 |
| *function* | _fail | - callback function(_sessionId) for informing about failed case of creating CashIn channel
 |
| *function* | _success | - callback function(_sessionId, _cashInId) for informing about successful case of creating CashIn channel
 |
| *function* | _update | - callback function(_sessionId, _cashInId, _amount) for informing about updating CashIn channel balance
 |
 11111111111111111111111111
 
## *function* closeCashInChannel

CashInApi.closeCashInChannel(_sessionId, _channelId, _fees, _parties, _success, _fail) `nonpayable` `41ad3c6f`

**Close CashIn channel. Require that the CashIn channel in ACTIVE status.
**

> CashIn channel will be assigned status CLOSE_REQUESTED; will proceed either to CLOSE or FAILED_TO_CLOSE after successful or fail confirmation respectively

Inputs

| **type** | **name** | **description** |
|-|-|-|
| *uint256* | _sessionId | - id of the session in which CashIn channel is closing
 |
| *uint256* | _channelId | - id of the CashIn channel to close
 |
| *uint256[]* | _fees | - array of fee amount per split definition party
 |
| *uint256[]* | _parties | - array of parties of split definition
 |
| *function* | _fail | - callback function(_sessionId, _channelId) for informing about successful case of closing CashIn channel
 |
| *function* | _success | - callback function(_sessionId, _channelId) for informing about failed case of closing CashIn channel
 |


## *function* balanceOf

CashInApi.balanceOf(_sessionId) `nonpayable` `44c31e2f`

**Get balance of the channel
**

>

Inputs

| **type** | **name** | **description** |
|-|-|-|
| *uint256* | _channelId | - the balance of the channel
 |

---