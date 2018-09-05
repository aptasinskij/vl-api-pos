package com.skysoft.vaultlogic.common.domain.application.events;

import lombok.Value;

@Value(staticConstructor = "of")
public class AppRegistered {

    public String name;

}
