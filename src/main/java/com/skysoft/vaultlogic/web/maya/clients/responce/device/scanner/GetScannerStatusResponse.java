package com.skysoft.vaultlogic.web.maya.clients.responce.device.scanner;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetScannerStatusResponse extends BaseResponse {

    @JsonProperty("scanner_enabled")
    private String scannerEnabled;

    private String qr;

}