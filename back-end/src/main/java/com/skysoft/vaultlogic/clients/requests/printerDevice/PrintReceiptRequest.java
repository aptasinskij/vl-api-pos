package com.skysoft.vaultlogic.clients.requests.printerDevice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.clients.requestModels.printerDevice.PrintReceiptBody;
import lombok.Builder;
import lombok.Value;

@Builder
@Value(staticConstructor = "of")
public class PrintReceiptRequest {

    private String id;
    private String data;

    @JsonProperty("wkhtmltopdf_params")
    private String params;

    public static PrintReceiptRequest of(PrintReceiptBody printReceipt) {
        return PrintReceiptRequest.builder()
                .id(printReceipt.getId())
                .data(printReceipt.getData())
                .params(printReceipt.getParams())
                .build();
    }

}