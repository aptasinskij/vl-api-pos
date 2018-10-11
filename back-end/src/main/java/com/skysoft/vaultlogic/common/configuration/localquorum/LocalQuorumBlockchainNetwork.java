package com.skysoft.vaultlogic.common.configuration.localquorum;

import com.skysoft.vaultlogic.common.configuration.BlockchainNetwork;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("local-quorum")
public class LocalQuorumBlockchainNetwork implements BlockchainNetwork {

    private static final String URL = "http://127.0.0.1:22000";
    private static final String ID = "77142";

    @Override
    public String getUrl() {
        return URL;
    }

    @Override
    public String getId() {
        return ID;
    }

}
