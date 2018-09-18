package com.skysoft.vaultlogic.common.domain.cashin.events;

import lombok.Value;

import java.math.BigInteger;

@Value(staticConstructor = "of")
public class CashInFailedToClose {

    BigInteger id;

    BigInteger sessionId;

}
