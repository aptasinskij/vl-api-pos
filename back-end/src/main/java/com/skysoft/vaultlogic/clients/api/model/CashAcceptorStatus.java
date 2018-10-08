package com.skysoft.vaultlogic.clients.api.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CashAcceptorStatus extends StatusCode {

    // TODO: 05.10.18 add test
    @JsonAlias({"disabled", "enabled"})
    private boolean acceptorStatus;

}