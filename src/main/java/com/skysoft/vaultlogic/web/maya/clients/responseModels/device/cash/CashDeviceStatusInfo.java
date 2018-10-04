package com.skysoft.vaultlogic.web.maya.clients.responseModels.device.cash;

import com.skysoft.vaultlogic.web.maya.clients.responseModels.BaseInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
public class CashDeviceStatusInfo extends BaseInfo {

    private Boolean acceptorEnabled;
    private Boolean dispenserEnabled;
    private Boolean recyclerEnabled;
    private BigDecimal actualAmount;
    private BigDecimal requestedAmount;

    public Boolean getAcceptorEnabled() {
        return acceptorEnabled;
    }

    public void setAcceptorEnabled(Boolean acceptorEnabled) {
        this.acceptorEnabled = acceptorEnabled;
    }

    public Boolean getDispenserEnabled() {
        return dispenserEnabled;
    }

    public void setDispenserEnabled(Boolean dispenserEnabled) {
        this.dispenserEnabled = dispenserEnabled;
    }

    public Boolean getRecyclerEnabled() {
        return recyclerEnabled;
    }

    public void setRecyclerEnabled(Boolean recyclerEnabled) {
        this.recyclerEnabled = recyclerEnabled;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(BigDecimal requestedAmount) {
        this.requestedAmount = requestedAmount;
    }
}