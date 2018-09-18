package com.skysoft.vaultlogic.web.service;

import com.skysoft.vaultlogic.common.domain.cashin.CashInChannel;
import com.skysoft.vaultlogic.common.domain.cashin.CashInChannel.Status;
import com.skysoft.vaultlogic.common.domain.cashin.CashInRepository;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.web.postback.impl.protocol.data.CashInsert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

@Service
public class JpaCashInService implements CashInService {

    private final SessionRepository sessionRepository;
    private final CashInRepository cashInRepository;

    @Autowired
    public JpaCashInService(SessionRepository sessionRepository, CashInRepository cashInRepository) {
        this.sessionRepository = sessionRepository;
        this.cashInRepository = cashInRepository;
    }

    @Override
    @Transactional
    public void createCashInChannel(BigInteger channelId, BigInteger sessionId, BigInteger status) {
        sessionRepository.findById(sessionId)
                .map(session -> CashInChannel.newChannel(channelId, session, Status.from(status)))
                .map(CashInChannel::markCreated)
                .ifPresent(cashInRepository::save);
    }

    @Override
    @Transactional
    public void confirmOpened(BigInteger channelId) {
        cashInRepository.findById(channelId).map(CashInChannel::markOpened).ifPresent(cashInRepository::save);
    }

    @Override
    @Transactional
    public void updateBalance(CashInsert event, String xToken) {
        cashInRepository.findBySession_xTokenAndStatus(xToken, Status.OPENED)
                .map(cashInChannel -> cashInChannel.updateBalance(event.getCurrentAmount()))
                .ifPresent(cashInRepository::save);
    }

    @Override
    @Transactional
    public void closeCashInChannel(BigInteger channelId, BigInteger sessionId) {
        cashInRepository.findById(channelId).map(cashInChannel -> cashInChannel.markHalfClosed(sessionId))
                .ifPresent(cashInRepository::save);
    }

    @Override
    @Transactional
    public void confirmClosed(BigInteger channelId) {
        cashInRepository.findByChannelId(channelId).map(CashInChannel::markClosed).ifPresent(cashInRepository::save);
    }

}
