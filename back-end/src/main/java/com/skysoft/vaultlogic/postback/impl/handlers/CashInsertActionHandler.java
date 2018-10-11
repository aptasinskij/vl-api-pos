package com.skysoft.vaultlogic.postback.impl.handlers;

import com.skysoft.vaultlogic.postback.mapper.DataMapper;
import com.skysoft.vaultlogic.postback.api.AbstractEventHandler;
import com.skysoft.vaultlogic.postback.api.EventEmptyResponse;
import com.skysoft.vaultlogic.postback.impl.factories.markers.CashActionHandler;
import com.skysoft.vaultlogic.postback.impl.protocol.data.CashInsert;
import com.skysoft.vaultlogic.services.CashInService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("cloud")
public class CashInsertActionHandler extends AbstractEventHandler<CashInsert, EventEmptyResponse> implements CashActionHandler {

    private static final String CASH_INSERT_HANDLER = "insert";

    private final CashInService cashInService;

    @Autowired
    protected CashInsertActionHandler(DataMapper dataMapper, CashInService cashInService) {
        super(CashInsert.class, dataMapper);
        this.cashInService = cashInService;
    }

    @Override
    protected EventEmptyResponse handleEvent(CashInsert eventData, String xToken) {
        log.info("[x] ---> More money inserted: {} for session: {}", eventData, xToken);
        cashInService.updateBalance(eventData, xToken);
        return new EventEmptyResponse();
    }

    @Override
    public String getName() {
        return CASH_INSERT_HANDLER;
    }

}
