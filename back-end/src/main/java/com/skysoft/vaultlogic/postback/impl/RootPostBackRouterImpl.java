package com.skysoft.vaultlogic.postback.impl;

import com.skysoft.vaultlogic.postback.api.AbstractEventRouter;
import com.skysoft.vaultlogic.postback.api.RootPostBackRouter;
import com.skysoft.vaultlogic.postback.api.factory.HandlerFactory;
import com.skysoft.vaultlogic.postback.impl.factories.markers.EventTypeRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("cloud")
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
