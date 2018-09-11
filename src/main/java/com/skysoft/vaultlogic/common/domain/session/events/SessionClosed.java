package com.skysoft.vaultlogic.common.domain.session.events;

import lombok.Value;

@Value(staticConstructor = "withXToken")
public class SessionClosed {
    String xToken;
}
