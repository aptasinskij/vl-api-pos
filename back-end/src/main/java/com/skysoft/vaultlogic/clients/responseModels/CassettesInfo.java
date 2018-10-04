package com.skysoft.vaultlogic.clients.responseModels;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class CassettesInfo {

    private String id;
    private BigDecimal count;
    private BigDecimal amount;
    private String type;
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
