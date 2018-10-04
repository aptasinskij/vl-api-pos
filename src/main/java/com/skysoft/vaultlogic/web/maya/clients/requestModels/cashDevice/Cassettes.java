package com.skysoft.vaultlogic.web.maya.clients.requestModels.cashDevice;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Cassettes {

    private BigDecimal amount;
    private BigDecimal denomination;
    private String type;

}