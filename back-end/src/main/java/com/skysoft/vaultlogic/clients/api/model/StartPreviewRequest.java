package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Builder
@Value(staticConstructor = "of")
public class StartPreviewRequest {

    @JsonProperty("qr_scanning")
    private Boolean qrScanning;

    @JsonProperty("light_enabled")
    private Boolean lightEnabled;

    public static StartPreviewRequest of(PreviewConfig startPreview) {
        return StartPreviewRequest.builder()
                .qrScanning(startPreview.getQrScanning())
                .lightEnabled(startPreview.getLightEnabled())
                .build();
    }

}