package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.*;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;

public interface KioskCashDevices {

    CashDevicesStatus getStatus(String xToken);

    RecyclerStatus getRecyclerStatus(String xToken);

    CashAcceptorStatus enableCashAcceptor(String xToken, CashAcceptorConfig cashAcceptorConfig);

    CashAcceptorStatus disableCashAcceptor(String xToken);

    DispensableAmount getDispensableAmount(String xToken, DispensableAmount amountToDispense);

    StatusCode dispenseCash(String xToken, DispenseCash dispenseCash);

}