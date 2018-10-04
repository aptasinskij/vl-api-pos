package com.skysoft.vaultlogic.clients.api.model;

import lombok.Value;

@Value(staticConstructor = "of")
public class PreviewConfig {

    private Boolean qrScanning;
    private Boolean lightEnabled;

    public static PreviewConfig defaultValues() {
        return PreviewConfig.of(false, false);
    }

}