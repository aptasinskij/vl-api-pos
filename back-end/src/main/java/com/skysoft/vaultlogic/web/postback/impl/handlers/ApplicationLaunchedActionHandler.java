package com.skysoft.vaultlogic.web.postback.impl.handlers;

import com.skysoft.vaultlogic.web.postback.mapper.DataMapper;
import com.skysoft.vaultlogic.web.postback.api.AbstractEventHandler;
import com.skysoft.vaultlogic.web.postback.api.EventEmptyResponse;
import com.skysoft.vaultlogic.web.postback.impl.factories.markers.ApplicationActionHandler;
import com.skysoft.vaultlogic.web.postback.impl.protocol.data.ApplicationLaunch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationLaunchedActionHandler extends AbstractEventHandler<ApplicationLaunch, EventEmptyResponse> implements ApplicationActionHandler {

    private static final String APPLICATION_LAUNCH_HANDLER = "launch";

    @Autowired
    protected ApplicationLaunchedActionHandler(DataMapper dataMapper) {
        super(ApplicationLaunch.class, dataMapper);
    }

    @Override
    protected EventEmptyResponse handleEvent(ApplicationLaunch eventData, String xToken) {
        log.info("[x] ---> New application session started: {}", xToken);
        return new EventEmptyResponse();
    }

    @Override
    public String getName() {
        return APPLICATION_LAUNCH_HANDLER;
    }

}
