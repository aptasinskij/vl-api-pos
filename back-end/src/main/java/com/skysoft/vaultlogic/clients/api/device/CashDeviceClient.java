package com.skysoft.vaultlogic.clients.api.device;

import com.skysoft.vaultlogic.clients.requestModels.cashDevice.DispenseCashBody;
import com.skysoft.vaultlogic.clients.requestModels.cashDevice.EnableCashAcceptorBody;
import com.skysoft.vaultlogic.clients.requestModels.cashDevice.GetDispensableAmountBody;
import com.skysoft.vaultlogic.clients.responseModels.BaseInfo;
import com.skysoft.vaultlogic.clients.responseModels.device.cash.*;

public interface CashDeviceClient {

    CashDeviceStatusInfo getCashDeviceStatus(String xToken);

    RecyclerDeviceStatusInfo getRecyclerDeviceStatus(String xToken);

    EnableCashAcceptorInfo enableCashAcceptor(String xToken, EnableCashAcceptorBody enableCashAcceptor);

    DisableCashAcceptorInfo disableCashAcceptor(String xToken);

    GetDispensableAmountInfo getDispensableAmount(String xToken, GetDispensableAmountBody getDispensableAmount);

    BaseInfo dispenseCash(String xToken, DispenseCashBody dispenseCash);

}