package com.skysoft.vaultlogic.web.maya.clients.responce.device.cash;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Valid
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecyclerDeviceStatusResponse {

    @NotNull
    private Boolean enable;

    @Valid
    private Cassettes cassettes;

    @NotNull
    @JsonProperty("current_amount")
    private BigDecimal currentAmount;

    @NotNull
    @JsonProperty("current_count")
    private BigDecimal currentCount;

    @Data
    private class Cassettes {

        @NotBlank
        private String id;
        private BigDecimal count;

        @NotBlank
        private String denomination;

        @NotNull
        private BigDecimal amount;

        @NotNull
        @JsonProperty("max_count")
        private BigDecimal maxCount;
    }

}