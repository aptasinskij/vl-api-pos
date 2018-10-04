package com.skysoft.vaultlogic.web.maya.clients.responces.device.cash;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
public class RecyclerDeviceStatusResponse {

    private Boolean enable;
    private Cassettes cassettes;

    @JsonProperty("current_amount")
    private BigDecimal currentAmount;

    @JsonProperty("current_count")
    private BigDecimal currentCount;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Cassettes getCassettes() {
        return cassettes;
    }

    public void setCassettes(Cassettes cassettes) {
        this.cassettes = cassettes;
    }

    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    public BigDecimal getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(BigDecimal currentCount) {
        this.currentCount = currentCount;
    }

    private class Cassettes {

        private String id;
        private BigDecimal count;
        private String denomination;
        private BigDecimal amount;

        @JsonProperty("max_count")
        private BigDecimal maxCount;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public BigDecimal getCount() {
            return count;
        }

        public void setCount(BigDecimal count) {
            this.count = count;
        }

        public String getDenomination() {
            return denomination;
        }

        public void setDenomination(String denomination) {
            this.denomination = denomination;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public BigDecimal getMaxCount() {
            return maxCount;
        }

        public void setMaxCount(BigDecimal maxCount) {
            this.maxCount = maxCount;
        }
    }

}