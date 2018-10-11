package com.skysoft.vaultlogic.common.domain.kiosk.mapper;

import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import org.springframework.stereotype.Component;

@Component
public class KioskMapperImpl implements KioskMapper {

    @Override
    public Kiosk fromKioskDevice(KioskDevice device) {
        return Kiosk.kiosk(device.getShortId(), device.getFormattedAddress(), device.getBusinessName(), device.getTimezone());
    }

}
