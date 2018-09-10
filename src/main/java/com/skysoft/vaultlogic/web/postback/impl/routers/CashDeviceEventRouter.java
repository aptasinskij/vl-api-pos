package com.skysoft.vaultlogic.web.postback.impl.routers;

import com.skysoft.vaultlogic.web.postback.api.AbstractEventRouter;
import com.skysoft.vaultlogic.web.postback.api.factory.HandlerFactory;
import com.skysoft.vaultlogic.web.postback.impl.factories.markers.CashActionHandler;
import com.skysoft.vaultlogic.web.postback.impl.factories.markers.DeviceTypeRouter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CashDeviceEventRouter extends AbstractEventRouter<CashActionHandler> implements DeviceTypeRouter {

    private static final String CASH_DEVICE_ROUTER = "cash";

    @Autowired
    protected CashDeviceEventRouter(HandlerFactory<CashActionHandler> handlerFactory) {
        super(handlerFactory);
    }

    @Override
    public String getName() {
        return CASH_DEVICE_ROUTER;
    }

}
