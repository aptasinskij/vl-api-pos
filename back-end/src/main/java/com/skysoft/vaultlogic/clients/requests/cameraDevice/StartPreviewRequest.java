package com.skysoft.vaultlogic.clients.requests.cameraDevice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.clients.requestModels.cameraDevice.StartPreviewBody;
import lombok.Builder;
import lombok.Value;

@Builder
@Value(staticConstructor = "of")
public class StartPreviewRequest {

    @JsonProperty("qr_scanning")
    private Boolean qrScanning;

    @JsonProperty("light_enabled")
    private Boolean lightEnabled;

    public static StartPreviewRequest of(StartPreviewBody startPreview) {
        return StartPreviewRequest.builder()
                .qrScanning(startPreview.getQrScanning())
                .lightEnabled(startPreview.getLightEnabled())
                .build();
    }

}