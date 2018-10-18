package com.skysoft.vaultlogic.postback.impl.handlers;

import com.skysoft.vaultlogic.postback.api.AbstractEventHandler;
import com.skysoft.vaultlogic.postback.api.EventEmptyResponse;
import com.skysoft.vaultlogic.postback.impl.factories.markers.CashActionHandler;
import com.skysoft.vaultlogic.postback.impl.protocol.data.CashAccept;
import com.skysoft.vaultlogic.postback.mapper.DataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("cloud")
public class CashAcceptActionHandler extends AbstractEventHandler<CashAccept, EventEmptyResponse> implements CashActionHandler {

    private static final String CASH_ACCEPT_HANDLER = "accept";

    @Autowired
    protected CashAcceptActionHandler(DataMapper dataMapper) {
        super(CashAccept.class, dataMapper);
    }

    @Override
    public EventEmptyResponse handleEvent(CashAccept eventData, String xToken) {
        log.info("[x] ---> Cash accept event: {} for session: {}", eventData, xToken);
        return new EventEmptyResponse();
    }

    @Override
    public String getName() {
        return CASH_ACCEPT_HANDLER;
    }

}
