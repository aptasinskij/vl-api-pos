package com.skysoft.vaultlogic.postback.impl.routers;

import com.skysoft.vaultlogic.postback.api.AbstractEventRouter;
import com.skysoft.vaultlogic.postback.api.factory.HandlerFactory;
import com.skysoft.vaultlogic.postback.impl.factories.markers.CameraActionHandler;
import com.skysoft.vaultlogic.postback.impl.factories.markers.DeviceTypeRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("cloud")
public class CameraEventRouter extends AbstractEventRouter<CameraActionHandler> implements DeviceTypeRouter {

    private static final String CAMERA_EVENT_ROUTER = "camera";

    @Autowired
    protected CameraEventRouter(HandlerFactory<CameraActionHandler> handlerFactory) {
        super(handlerFactory);
    }

    @Override
    public String getName() {
        return CAMERA_EVENT_ROUTER;
    }

}