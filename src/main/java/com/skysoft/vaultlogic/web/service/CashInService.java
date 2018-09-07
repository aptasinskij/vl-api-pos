package com.skysoft.vaultlogic.web.service;

import java.math.BigInteger;

public interface CashInService {

    void createCashInChannel(BigInteger id, BigInteger sessionId, BigInteger status);

    void confirmOpened(BigInteger channelId);

    void closeCashInChannel(BigInteger channelId, String xToken);

    void confirmClosed(BigInteger channelId);

}
