package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.Receipt;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.clients.api.model.ReceiptIdUrl;
import io.vavr.control.Either;

public interface KioskPrinter {

    Either<Throwable, ReceiptIdUrl> createReceipt(String xToken);

    Either<Throwable, StatusCode> printReceipt(String xToken, Receipt receipt);

}