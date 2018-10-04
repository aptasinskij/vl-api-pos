package com.skysoft.vaultlogic.web.maya.clients.responseModels.device.cash;

import com.skysoft.vaultlogic.web.maya.clients.responseModels.BaseInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class EnableCashAcceptorInfo extends BaseInfo {

    private Boolean enabled;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}