package com.skysoft.vaultlogic.web.maya.clients.responce.device.cash;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CashDeviceStatusResponse extends BaseResponse {

    @JsonProperty("acceptor_enabled")
    private Boolean acceptorEnabled;

    @JsonProperty("dispenser_enabled")
    private Boolean dispenserEnabled;

    @JsonProperty("recycler_enabled")
    private Boolean recyclerEnabled;

    @JsonProperty("actualAmount")
    private Boolean actual_amount;

    @JsonProperty("requested_amount")
    private Boolean requestedAmount;

}