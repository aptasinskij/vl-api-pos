package com.skysoft.vaultlogic.services;

import com.skysoft.vaultlogic.common.domain.application.projections.SmartContractApplication;
import com.skysoft.vaultlogic.contracts.ApplicationManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

@Slf4j
@Service
@AllArgsConstructor
public class BlockchainAppService {

    private final ApplicationManager appService;

    public void saveApplication(SmartContractApplication app) {
        appService.registerApplication(BigInteger.valueOf(app.getId()), app.getName(), app.getOwnerAddress(), app.getUri(), app.getAddress())
                .sendAsync()
                .thenAccept(this::logSuccessSave)
                .exceptionally(this::logFailSave);
    }

    private Void logFailSave(Throwable throwable) {
        log.error("[x]---> Error during app registration. {}", throwable.getMessage());
        return null;
    }

    private void logSuccessSave(TransactionReceipt tx) {
        log.info("[x] --> Application registered. TX: {}", tx.getTransactionHash());
    }

}
