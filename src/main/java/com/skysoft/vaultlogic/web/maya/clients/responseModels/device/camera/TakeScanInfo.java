package com.skysoft.vaultlogic.web.maya.clients.responseModels.device.camera;

import com.skysoft.vaultlogic.web.maya.clients.responseModels.BaseInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TakeScanInfo extends BaseInfo {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}