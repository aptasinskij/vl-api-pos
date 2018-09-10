package com.skysoft.vaultlogic.web.postback.impl.handlers;

import com.skysoft.vaultlogic.common.mapper.DataMapper;
import com.skysoft.vaultlogic.web.postback.api.AbstractEventHandler;
import com.skysoft.vaultlogic.web.postback.api.EventEmptyResponse;
import com.skysoft.vaultlogic.web.postback.impl.factories.markers.CashActionHandler;
import com.skysoft.vaultlogic.web.postback.impl.protocol.data.CashInsert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

@Slf4j
@Component
public class CashInsertActionHandler extends AbstractEventHandler<CashInsert, EventEmptyResponse> implements CashActionHandler {

    private static final String CASH_INSERT_HANDLER = "insert";

    @Autowired
    protected CashInsertActionHandler(DataMapper dataMapper) {
        super(CashInsert.class, dataMapper);
    }

    @Override
    protected EventEmptyResponse handleEvent(CashInsert eventData, String xToken) {
        log.info("[x] ---> More money inserted: {} for session: {}", eventData, xToken);
//        vaultLogic.addMoneyToChannel(xToken, eventData.getCurrentAmount().toBigInteger())
//                .sendAsync()
//                .thenAccept(this::moneyAddedSuccessfully)
//                .exceptionally(this::failedToAddMoney);
        return new EventEmptyResponse();
    }

    private void moneyAddedSuccessfully(TransactionReceipt transactionReceipt) {
        log.info("[x] ---> Succeed in adding money to channel! TxHash: {}", transactionReceipt.getTransactionHash());
    }

    private Void failedToAddMoney(Throwable throwable) {
        log.error("[x] ---> Failed in adding money to channel! Reason: {}", throwable.getMessage(), throwable);
        return null;
    }

    @Override
    public String getName() {
        return CASH_INSERT_HANDLER;
    }

}
