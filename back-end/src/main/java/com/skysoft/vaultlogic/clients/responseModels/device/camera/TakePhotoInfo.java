package com.skysoft.vaultlogic.clients.responseModels.device.camera;

import com.skysoft.vaultlogic.clients.responseModels.BaseInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TakePhotoInfo extends BaseInfo {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}