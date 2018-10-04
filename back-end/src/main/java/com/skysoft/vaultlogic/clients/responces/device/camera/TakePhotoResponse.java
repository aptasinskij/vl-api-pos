package com.skysoft.vaultlogic.clients.responces.device.camera;

import com.skysoft.vaultlogic.clients.responces.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class TakePhotoResponse extends BaseResponse {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}