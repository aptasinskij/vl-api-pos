package com.skysoft.vaultlogic.common.domain.kiosk.events;

import lombok.Value;

@Value(staticConstructor = "of")
public class KioskCreated {

    private String shortId;

    private String address;

    private String name;

    private String timeZone;

}
