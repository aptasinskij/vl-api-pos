package com.skysoft.vaultlogic.clients.api;

import com.skysoft.vaultlogic.clients.api.model.PreviewConfig;
import com.skysoft.vaultlogic.clients.api.model.ScanLight;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.clients.api.model.PhotoId;
import com.skysoft.vaultlogic.clients.api.model.Preview;
import com.skysoft.vaultlogic.clients.api.model.ScanId;

public interface KioskCamera {

    PhotoId takePhoto(String xToken);

    ScanId takeScan(String xToken, ScanLight scanLight);

    Preview startPreview(String xToken, PreviewConfig startPreview);

    StatusCode stopPreview(String xToken);

}