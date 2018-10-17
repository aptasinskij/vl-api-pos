package com.skysoft.vaultlogic.postback.impl.handlers;

import com.skysoft.vaultlogic.postback.mapper.DataMapper;
import com.skysoft.vaultlogic.postback.api.AbstractEventHandler;
import com.skysoft.vaultlogic.postback.api.EventEmptyResponse;
import com.skysoft.vaultlogic.postback.impl.factories.markers.ApplicationActionHandler;
import com.skysoft.vaultlogic.postback.impl.protocol.data.ApplicationClose;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("cloud")
public class ApplicationClosedActionHandler extends AbstractEventHandler<ApplicationClose, EventEmptyResponse> implements ApplicationActionHandler {

    private static final String APPLICATION_CLOSE_HANDLER = "close";

    @Autowired
    protected ApplicationClosedActionHandler(DataMapper dataMapper) {
        super(ApplicationClose.class, dataMapper);
    }

    @Override
    public EventEmptyResponse handleEvent(ApplicationClose eventData, String xToken) {
        log.info("[x] ---> Application session: {} closed", xToken);
        return new EventEmptyResponse();
    }

    @Override
    public String getName() {
        return APPLICATION_CLOSE_HANDLER;
    }

}
