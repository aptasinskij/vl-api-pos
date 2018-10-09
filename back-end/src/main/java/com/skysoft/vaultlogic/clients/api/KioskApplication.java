package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import io.vavr.Tuple2;
import io.vavr.control.Either;

public interface KioskApplication {

    Either<Throwable, StatusCode> launchApplication(String xToken);

    Either<Throwable, StatusCode> keepAlive(String xToken, String keepAlive);

    Either<Throwable, StatusCode> clientActivity(String xToken);

    Either<Throwable, StatusCode> closeApplication(String xToken);

}