package com.skysoft.vaultlogic.web.maya.clients.requestModels.cashDevice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DispenseCashBody {

    private BigDecimal requestedAmount;
    private Cassettes cassettes;

}