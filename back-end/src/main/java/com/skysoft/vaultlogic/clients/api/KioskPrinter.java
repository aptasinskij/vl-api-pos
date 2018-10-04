package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.Receipt;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.clients.api.model.ReceiptIdUrl;

public interface KioskPrinter {

    ReceiptIdUrl createReceipt(String xToken);

    StatusCode printReceipt(String xToken, Receipt receipt);

}