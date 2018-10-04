package com.skysoft.vaultlogic.clients.requests.cameraDevice;

import com.skysoft.vaultlogic.clients.requestModels.cameraDevice.TakeScanBody;
import lombok.Builder;
import lombok.Value;

@Builder
@Value(staticConstructor = "of")
public class TakeScanRequest {

    private String light;

    public static TakeScanRequest of(TakeScanBody takeScan) {
        return TakeScanRequest.builder()
                .light(takeScan.getLight().getLightAsString())
                .build();
    }

}