package com.skysoft.vaultlogic.web.maya.clients.responce.device.cash;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.web.maya.clients.responce.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetDispensableAmountResponse extends BaseResponse {

    private Cassettes cassettes;

    @JsonProperty("available_amount")
    private BigDecimal availableAmount;

    @Data
    private class Cassettes {

        private String id;
        private BigDecimal count;
        private BigDecimal amount;
        private String type;

        @JsonProperty("max_count")
        private BigDecimal maxCount;
    }
}