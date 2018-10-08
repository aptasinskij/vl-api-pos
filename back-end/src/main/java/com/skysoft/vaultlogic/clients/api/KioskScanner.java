package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.ScannerStatus;
import io.vavr.control.Either;

public interface KioskScanner {

    Either<Throwable, ScannerStatus> getStatus(String xToken);

}