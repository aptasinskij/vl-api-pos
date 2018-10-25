package com.skysoft.vaultlogic.services;

import com.skysoft.vaultlogic.common.domain.cashin.CashInChannel;
import com.skysoft.vaultlogic.common.domain.cashin.CashInChannel.Status;
import com.skysoft.vaultlogic.common.domain.cashin.CashInRepository;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.postback.impl.protocol.data.CashInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
public class CashInServiceImpl implements CashInService {

    private final SessionRepository sessionRepository;
    private final CashInRepository cashInRepository;

    @Autowired
    public CashInServiceImpl(SessionRepository sessionRepository, CashInRepository cashInRepository) {
        this.sessionRepository = sessionRepository;
        this.cashInRepository = cashInRepository;
    }

    @Override
    @Transactional
    public void createCashInChannel(BigInteger channelId, BigInteger sessionId, BigInteger maxBalance, BigInteger status) {
        sessionRepository.findById(sessionId)
                .map(session -> CashInChannel.newChannel(channelId, session, maxBalance, Status.from(status)))
                .map(CashInChannel::markCreating)
                .ifPresent(cashInRepository::save);
    }

    @Override
    @Transactional
    public void confirmOpened(BigInteger channelId) {
        cashInRepository.findById(channelId).map(CashInChannel::markActive).ifPresent(cashInRepository::save);
    }

    @Override
    @Transactional
    public void updateBalance(CashInsert event, String xToken) {
        cashInRepository.findBySession_xTokenAndStatus(xToken, Status.ACTIVE)
                .map(cashInChannel -> cashInChannel.updateBalance(event.getCurrentAmount()))
                .ifPresent(cashInRepository::save);
    }

    @Override
    @Transactional
    public void closeCashInChannel(BigInteger channelId, BigInteger sessionId) {
        cashInRepository.findById(channelId).map(CashInChannel::markCloseRequested)
                .ifPresent(cashInRepository::save);
    }

    @Override
    @Transactional
    public void confirmClosed(BigInteger channelId) {
        cashInRepository.findByChannelId(channelId).map(CashInChannel::markClosed).ifPresent(cashInRepository::save);
    }

}
