package com.skysoft.vaultlogic.web.maya.clients.responseModels.device.cash;

import com.skysoft.vaultlogic.web.maya.clients.responseModels.BaseInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DisableCashAcceptorInfo extends BaseInfo {

    private Boolean disabled;

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}