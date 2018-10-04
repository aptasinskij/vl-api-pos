package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.ScannerStatus;

public interface KioskScanner {

    ScannerStatus getStatus(String xToken);

}