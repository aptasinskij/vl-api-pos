package com.skysoft.vaultlogic.common.domain.session.projections;

import org.springframework.beans.factory.annotation.Value;

public interface SmartContractSession {

    @Value("#{target.id}")
    Long getId();

    @Value("#{target.application.id}")
    Long getApplicationId();

    @Value("#{target.xToken}")
    String getXToken();

}
