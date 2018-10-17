package com.skysoft.vaultlogic.postback.impl.handlers;

import com.skysoft.vaultlogic.postback.mapper.DataMapper;
import com.skysoft.vaultlogic.postback.api.AbstractEventHandler;
import com.skysoft.vaultlogic.postback.api.EventEmptyResponse;
import com.skysoft.vaultlogic.postback.impl.factories.markers.ApplicationActionHandler;
import com.skysoft.vaultlogic.postback.impl.protocol.data.ApplicationLaunch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile("cloud")
public class ApplicationLaunchedActionHandler extends AbstractEventHandler<ApplicationLaunch, EventEmptyResponse> implements ApplicationActionHandler {

    private static final String APPLICATION_LAUNCH_HANDLER = "launch";

    @Autowired
    protected ApplicationLaunchedActionHandler(DataMapper dataMapper) {
        super(ApplicationLaunch.class, dataMapper);
    }

    @Override
    public EventEmptyResponse handleEvent(ApplicationLaunch eventData, String xToken) {
        log.info("[x] ---> New application session started: {}", xToken);
        return new EventEmptyResponse();
    }

    @Override
    public String getName() {
        return APPLICATION_LAUNCH_HANDLER;
    }

}
