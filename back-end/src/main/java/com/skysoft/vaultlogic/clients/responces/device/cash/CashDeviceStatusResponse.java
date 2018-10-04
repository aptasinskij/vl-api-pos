package com.skysoft.vaultlogic.clients.responces.device.cash;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.clients.responces.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
public class CashDeviceStatusResponse extends BaseResponse {

    @JsonProperty("acceptor_enabled")
    private Boolean acceptorEnabled;

    @JsonProperty("dispenser_enabled")
    private Boolean dispenserEnabled;

    @JsonProperty("recycler_enabled")
    private Boolean recyclerEnabled;

    @JsonProperty("actual_amount")
    private BigDecimal actualAmount;

    @JsonProperty("requested_amount")
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