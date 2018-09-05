package com.skysoft.vaultlogic.common.domain.application.projections;

import org.springframework.beans.factory.annotation.Value;

public interface SmartContractApplication {

    Long getId();

    String getName();

    @Value("#{target.owner.address}")
    String getOwnerAddress();

    String getAddress();

    String getUri();

}
