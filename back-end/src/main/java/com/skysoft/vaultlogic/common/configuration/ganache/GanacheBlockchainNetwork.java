package com.skysoft.vaultlogic.common.configuration.ganache;

import com.skysoft.vaultlogic.common.configuration.BlockchainNetwork;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("ganache")
public class GanacheBlockchainNetwork implements BlockchainNetwork {

    private static final String URL = "http://localhost:7545";
    private static final String ID = "5777";

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public String getId() {
        return ID;
    }


}
