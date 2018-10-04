package com.skysoft.vaultlogic.clients.api.device;

import com.skysoft.vaultlogic.clients.requestModels.printerDevice.PrintReceiptBody;
import com.skysoft.vaultlogic.clients.responseModels.BaseInfo;
import com.skysoft.vaultlogic.clients.responseModels.device.printer.CreateReceiptUrlInfo;

public interface PrinterDeviceClient {

    CreateReceiptUrlInfo createReceipt(String xToken);

    BaseInfo printReceipt(String xToken, PrintReceiptBody printReceipt);

}