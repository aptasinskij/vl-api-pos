package com.skysoft.vaultlogic.web.maya.clients.api.device;

import com.skysoft.vaultlogic.web.maya.clients.requestModels.printerDevice.PrintReceiptBody;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.BaseInfo;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.device.printer.CreateReceiptUrlInfo;

public interface PrinterDeviceClient {

    CreateReceiptUrlInfo createReceipt(String xToken);

    BaseInfo printReceipt(String xToken, PrintReceiptBody printReceipt);

}