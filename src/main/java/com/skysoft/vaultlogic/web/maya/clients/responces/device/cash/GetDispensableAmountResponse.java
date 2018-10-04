package com.skysoft.vaultlogic.web.maya.clients.responces.device.cash;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.skysoft.vaultlogic.web.maya.clients.responces.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class GetDispensableAmountResponse extends BaseResponse {

    private Cassettes cassettes;

    @JsonProperty("available_amount")
    private BigDecimal availableAmount;

    public Cassettes getCassettes() {
        return cassettes;
    }

    public void setCassettes(Cassettes cassettes) {
        this.cassettes = cassettes;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    private class Cassettes {

        private String id;
        private BigDecimal count;
        private BigDecimal amount;
        private String type;

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

        public BigDecimal getAmount() {
            return amount;
        }

        public void setAmount(BigDecimal amount) {
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public BigDecimal getMaxCount() {
            return maxCount;
        }

        public void setMaxCount(BigDecimal maxCount) {
            this.maxCount = maxCount;
        }
    }
}