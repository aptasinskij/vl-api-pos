package com.skysoft.vaultlogic.common.domain.application.projections;

import com.skysoft.vaultlogic.common.domain.application.Application;

import java.util.function.Predicate;

public interface AppUriStatus {

    String getUri();

    Application.Status getStatus();

    static Predicate<AppUriStatus> enabled() {
        return projection -> projection.getStatus().equals(Application.Status.ENABLED);
    }

}
