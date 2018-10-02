package com.skysoft.vaultlogic.web.postback.impl.routers;

import com.skysoft.vaultlogic.web.postback.api.AbstractEventRouter;
import com.skysoft.vaultlogic.web.postback.api.factory.HandlerFactory;
import com.skysoft.vaultlogic.web.postback.impl.factories.markers.ApplicationActionHandler;
import com.skysoft.vaultlogic.web.postback.impl.factories.markers.EventTypeRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationEventRouter extends AbstractEventRouter<ApplicationActionHandler> implements EventTypeRouter {

    private static final String APPLICATION_EVENT_ROUTER = "application";

    @Autowired
    protected ApplicationEventRouter(HandlerFactory<ApplicationActionHandler> handlerFactory) {
        super(handlerFactory);
    }

    @Override
    public String getName() {
        return APPLICATION_EVENT_ROUTER;
    }

}
