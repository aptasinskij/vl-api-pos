package com.skysoft.vaultlogic.web.postback.impl;

import com.skysoft.vaultlogic.web.postback.api.AbstractEventRouter;
import com.skysoft.vaultlogic.web.postback.api.RootPostBackRouter;
import com.skysoft.vaultlogic.web.postback.api.factory.HandlerFactory;
import com.skysoft.vaultlogic.web.postback.impl.factories.markers.EventTypeRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RootPostBackRouterImpl extends AbstractEventRouter<EventTypeRouter> implements RootPostBackRouter {

    private static final String ROOT_ROUTER_NAME = "root";

    @Autowired
    protected RootPostBackRouterImpl(HandlerFactory<EventTypeRouter> handlerFactory) {
        super(handlerFactory);
    }

    @Override
    public String getName() {
        return ROOT_ROUTER_NAME;
    }

}
