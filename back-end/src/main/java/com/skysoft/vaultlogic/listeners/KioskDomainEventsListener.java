package com.skysoft.vaultlogic.listeners;

import com.skysoft.vaultlogic.common.domain.kiosk.events.KioskCreated;
import com.skysoft.vaultlogic.contracts.KioskStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Slf4j
@Component
public class KioskDomainEventsListener {

    private final KioskStorage kioskStorage;

    @Autowired
    public KioskDomainEventsListener(KioskStorage kioskStorage) {
        this.kioskStorage = kioskStorage;
    }

    @Async
    @TransactionalEventListener
    public void created(KioskCreated event) {
        kioskStorage.save(event.getShortId(), event.getAddress(), event.getName(), event.getTimeZone())
                .observable().subscribe(this::onNext, this::onError);
    }

    private void onNext(TransactionReceipt transactionReceipt) {
        log.info("[x]---> KioskDevicesClient saved into blockchain. TX HASH: {}", transactionReceipt.getTransactionHash());
    }

    private void onError(Throwable throwable) {
        log.error("[x]---> Failed to save kiosk.");
    }

}
