package com.skysoft.vaultlogic.listeners;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RemoteRequestEmulator {

    private RemoteRequestEmulator() {}

    public static void mayaRequest() {
        try {
            log.info("[x] Sending request to MAYA");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.warn("[x] Thread was interrupted", e);
            Thread.currentThread().interrupt();
        }
    }

}
