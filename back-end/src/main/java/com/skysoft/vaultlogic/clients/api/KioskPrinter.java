package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.Receipt;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.clients.api.model.ReceiptIdUrl;
import io.vavr.control.Either;
import io.vavr.control.Try;

public interface KioskPrinter {

    Try<ReceiptIdUrl> createReceipt(String xToken);

    Try<StatusCode> printReceipt(String xToken, Receipt receipt);

}