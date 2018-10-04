package com.skysoft.vaultlogic.clients.requestModels.cashDevice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnableCashAcceptorBody {

    private BigDecimal requestedAmount;
    private List<Float> acceptableDenominations;
    private Boolean acceptanceControl;

}