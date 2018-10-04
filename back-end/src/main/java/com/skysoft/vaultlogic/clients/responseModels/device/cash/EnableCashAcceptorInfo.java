package com.skysoft.vaultlogic.clients.responseModels.device.cash;

import com.skysoft.vaultlogic.clients.responseModels.BaseInfo;
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