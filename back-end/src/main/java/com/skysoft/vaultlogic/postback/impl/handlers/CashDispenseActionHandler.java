package com.skysoft.vaultlogic.postback.impl.handlers;

import com.skysoft.vaultlogic.postback.api.AbstractEventHandler;
import com.skysoft.vaultlogic.postback.api.EventEmptyResponse;
import com.skysoft.vaultlogic.postback.impl.factories.markers.CashActionHandler;
import com.skysoft.vaultlogic.postback.impl.protocol.data.CashDispense;
import com.skysoft.vaultlogic.postback.mapper.DataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("cloud")
public class CashDispenseActionHandler extends AbstractEventHandler<CashDispense, EventEmptyResponse> implements CashActionHandler {

    private static final String CASH_DISPENSE_HANDLER = "dispense";

    @Autowired
    protected CashDispenseActionHandler(DataMapper dataMapper) {
        super(CashDispense.class, dataMapper);
    }

    @Override
    public EventEmptyResponse handleEvent(CashDispense eventData, String xToken) {
        log.info("[x] ---> Cash dispense event: {} for session: {}", eventData, xToken);
        return new EventEmptyResponse();
    }

    @Override
    public String getName() {
        return CASH_DISPENSE_HANDLER;
    }

}
