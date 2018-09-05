package com.skysoft.vaultlogic.web.handler;

import com.skysoft.vaultlogic.common.domain.application.events.AppRegistered;

public interface AppEventsHandler {

    void handleAppRegisteredEvent(AppRegistered event);

}
