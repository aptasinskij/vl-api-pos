package com.skysoft.vaultlogic.web.service;

import com.skysoft.vaultlogic.web.postback.impl.protocol.data.CashInsert;

import java.math.BigInteger;

public interface CashInService {

    void createCashInChannel(BigInteger id, BigInteger sessionId, BigInteger status);

    void confirmOpened(BigInteger channelId);

    void updateBalance(CashInsert event, String xToken);

    void closeCashInChannel(BigInteger channelId, String xToken);

    void confirmClosed(BigInteger channelId);

}
