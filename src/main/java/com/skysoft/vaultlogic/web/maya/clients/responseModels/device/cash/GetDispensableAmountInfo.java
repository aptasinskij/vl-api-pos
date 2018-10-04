package com.skysoft.vaultlogic.web.maya.clients.responseModels.device.cash;

import com.skysoft.vaultlogic.web.maya.clients.responseModels.BaseInfo;
import com.skysoft.vaultlogic.web.maya.clients.responseModels.CassettesInfo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
public class GetDispensableAmountInfo extends BaseInfo {

    // FIXME: 03.10.18 cannot map from response
    private CassettesInfo cassettes;
    private BigDecimal availableAmount;

}