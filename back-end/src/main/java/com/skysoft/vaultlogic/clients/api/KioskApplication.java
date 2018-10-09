package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import io.vavr.control.Either;
import io.vavr.control.Try;

public interface KioskApplication {

    Try<StatusCode> launchApplication(String xToken);

    Either<Throwable, StatusCode> keepAlive(String xToken, String keepAlive);

    Either<Throwable, StatusCode> clientActivity(String xToken);

    Either<Throwable, StatusCode> closeApplication(String xToken);

}