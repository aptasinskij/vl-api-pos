package com.skysoft.vaultlogic.web.maya.clients.requestModels.cameraDevice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartPreviewBody {

    private Boolean qrScanning;
    private Boolean lightEnabled;

}