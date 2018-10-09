package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.*;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import io.vavr.control.Either;

public interface KioskCashDevices {

    Either<Throwable, CashDevicesStatus> getStatus(String xToken);

    Either<Throwable, RecyclerStatus> getRecyclerStatus(String xToken);

    Either<Throwable, CashAcceptorStatus> enableCashAcceptor(String xToken, CashAcceptorConfig cashAcceptorConfig);

    Either<Throwable, CashAcceptorStatus> disableCashAcceptor(String xToken);

    Either<Throwable, DispensableAmount> getDispensableAmount(String xToken, DispensableAmount amountToDispense);

    Either<Throwable, StatusCode> dispenseCash(String xToken, DispenseCash dispenseCash);

}