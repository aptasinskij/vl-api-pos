package com.skysoft.vaultlogic.services;

import com.skysoft.vaultlogic.postback.impl.protocol.data.CashInsert;

import java.math.BigInteger;

public interface CashInService {

    void createCashInChannel(BigInteger channelId, BigInteger sessionId, BigInteger maxBalance, BigInteger status);

    void confirmOpened(BigInteger channelId);

    void updateBalance(CashInsert event, String xToken);

    void closeCashInChannel(BigInteger channelId, BigInteger sessionId);

    void confirmClosed(BigInteger channelId);

}
