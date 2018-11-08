package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.*;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import io.vavr.control.Either;
import io.vavr.control.Try;

public interface KioskCashDevices {

    Either<Throwable, CashDevicesStatus> getStatus(String xToken);

    Either<Throwable, RecyclerStatus> getRecyclerStatus(String xToken);

    Try<CashAcceptorStatus> enableCashAcceptor(String xToken, CashAcceptorConfig cashAcceptorConfig);

    Try<CashAcceptorStatus> disableCashAcceptor(String xToken);

    Try<DispensableAmount> getDispensableAmount(String xToken, DispensableAmount amountToDispense);

    Either<Throwable, StatusCode> dispenseCash(String xToken, DispenseCash dispenseCash);

}