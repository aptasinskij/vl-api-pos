package com.skysoft.vaultlogic.web.maya.clients.requests.cameraDevice;

import com.skysoft.vaultlogic.web.maya.clients.requestModels.cameraDevice.TakeScanBody;
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