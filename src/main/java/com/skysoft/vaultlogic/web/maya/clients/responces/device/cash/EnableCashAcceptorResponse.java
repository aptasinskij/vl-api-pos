package com.skysoft.vaultlogic.web.maya.clients.responces.device.cash;

import com.skysoft.vaultlogic.web.maya.clients.responces.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EnableCashAcceptorResponse extends BaseResponse {

    private Boolean enabled;


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}