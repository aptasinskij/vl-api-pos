package com.skysoft.vaultlogic.clients.responseModels.device.cash;

import com.skysoft.vaultlogic.clients.responseModels.BaseInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
public class RecyclerDeviceStatusInfo extends BaseInfo {

    private Boolean enable;
    // FIXME: 03.10.18 cannot map from response
//    private CassettesInfo cassettes;
    private BigDecimal currentAmount;
    private BigDecimal currentCount;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

//    public CassettesInfo getCassettes() {
//        return cassettes;
//    }
//
//    public void setCassettes(CassettesInfo cassettes) {
//        this.cassettes = cassettes;
//    }

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

        private String id;
        private BigDecimal count;
        private String denomination;
        private BigDecimal amount;
        private BigDecimal maxCount;
    }

}