package com.skysoft.vaultlogic.web.postback.impl.routers;

import com.skysoft.vaultlogic.web.postback.api.AbstractEventRouter;
import com.skysoft.vaultlogic.web.postback.api.factory.HandlerFactory;
import com.skysoft.vaultlogic.web.postback.impl.factories.markers.DeviceTypeRouter;
import com.skysoft.vaultlogic.web.postback.impl.factories.markers.EventTypeRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceEventRouter extends AbstractEventRouter<DeviceTypeRouter> implements EventTypeRouter {

    private static final String DEVICE_EVENT_ROUTER = "device";

    @Autowired
    protected DeviceEventRouter(HandlerFactory<DeviceTypeRouter> handlerFactory) {
        super(handlerFactory);
    }

    @Override
    public String getName() {
        return DEVICE_EVENT_ROUTER;
    }

}
