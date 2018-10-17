package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.PreviewConfig;
import com.skysoft.vaultlogic.clients.api.model.ScanLight;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.clients.api.model.PhotoId;
import com.skysoft.vaultlogic.clients.api.model.Preview;
import com.skysoft.vaultlogic.clients.api.model.ScanId;
import io.vavr.control.Either;
import io.vavr.control.Try;

public interface KioskCamera {

    Either<Throwable, PhotoId> takePhoto(String xToken);

    Either<Throwable, ScanId> takeScan(String xToken, ScanLight scanLight);

    Try<Preview> startPreview(String xToken, PreviewConfig startPreview);

    Either<Throwable, StatusCode> stopPreview(String xToken);

}