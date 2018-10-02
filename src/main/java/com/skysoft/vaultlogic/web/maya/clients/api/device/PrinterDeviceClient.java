package com.skysoft.vaultlogic.web.maya.clients.api.device;

import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import com.skysoft.vaultlogic.web.maya.clients.responce.device.printer.CreateReceiptUrlResponse;
import org.springframework.http.ResponseEntity;

public interface PrinterDeviceClient {

    ResponseEntity<CreateReceiptUrlResponse> createReceipt(String xToken);

    ResponseEntity<BaseResponse> printReceipt(String xToken);

}