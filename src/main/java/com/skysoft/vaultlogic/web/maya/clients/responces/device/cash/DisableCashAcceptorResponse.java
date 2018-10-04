package com.skysoft.vaultlogic.web.maya.clients.responces.device.cash;

import com.skysoft.vaultlogic.web.maya.clients.responces.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class DisableCashAcceptorResponse extends BaseResponse {

    private Boolean disabled;

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
}