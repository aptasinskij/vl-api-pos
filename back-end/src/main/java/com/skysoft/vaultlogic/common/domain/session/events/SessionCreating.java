package com.skysoft.vaultlogic.common.domain.session.events;

import lombok.Value;

@Value(staticConstructor = "of")
public class SessionCreating {

    public String xToken;

}
