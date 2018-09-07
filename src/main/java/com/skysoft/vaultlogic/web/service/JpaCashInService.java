package com.skysoft.vaultlogic.web.service;

import com.skysoft.vaultlogic.common.domain.cashin.CashInChannel.Status;
import com.skysoft.vaultlogic.common.domain.cashin.CashInRepository;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

import static com.skysoft.vaultlogic.common.domain.cashin.CashInChannel.newChannel;

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
    public void createCashInChannel(BigInteger id, BigInteger sessionId, BigInteger status) {
        sessionRepository.findById(sessionId.longValue())
                .ifPresent(session -> cashInRepository.save(newChannel(id, session, Status.from(status)).emitCreated()));
    }

    @Override
    @Transactional
    public void confirmOpened(BigInteger channelId) {
        cashInRepository.findByChannelId(channelId)
                .ifPresent(channel -> cashInRepository.save(channel.markOpened()));
    }

    @Override
    @Transactional
    public void closeCashInChannel(BigInteger channelId, String xToken) {
        cashInRepository.findByChannelId(channelId)
                .ifPresent(channel -> cashInRepository.save(channel.markHalfClosed(xToken)));
    }

    @Override
    @Transactional
    public void confirmClosed(BigInteger channelId) {
        cashInRepository.findByChannelId(channelId)
                .ifPresent(channel -> cashInRepository.save(channel.markClosed()));
    }

}
