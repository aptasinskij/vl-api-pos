package com.skysoft.vaultlogic.web.maya.clients.requestModels.printerDevice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrintReceiptBody {

    private String id;
    private String data;
    private String params;

}