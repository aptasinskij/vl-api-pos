package com.skysoft.vaultlogic.services.quorum;

import com.skysoft.vaultlogic.clients.api.KioskApplicationClient;
import com.skysoft.vaultlogic.common.domain.application.ApplicationRepository;
import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.services.KioskService;
import io.vavr.control.Try;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.charThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class QuorumSessionServiceTest {

    @TestConfiguration
    static class QuorumSessionServiceTestConfiguration {

        @MockBean
        private SessionRepository sessionRepository;

        @MockBean
        private ApplicationRepository applicationRepository;

        @MockBean
        private KioskService kioskService;

        @MockBean
        private KioskApplicationClient kioskApplicationClient;

        @Bean
        public QuorumSessionService quorumSessionService() {
            return new QuorumSessionService(sessionRepository, applicationRepository, kioskService, kioskApplicationClient);
        }

    }

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private KioskService kioskService;

    @Autowired
    private KioskApplicationClient kioskApplicationClient;

    @Autowired
    private QuorumSessionService quorumSessionService;

    @Test(expected = RuntimeException.class)
    public void givenFailedToLaunchApplication_whenError_thanCorrect() {
        when(kioskApplicationClient.launchApplication(anyString())).thenReturn(Try.failure(new RuntimeException()));
        quorumSessionService.createSession(BigInteger.ZERO, "xToken");
        verify(kioskService, never()).resolveKioskForSession(anyString());
        verify(applicationRepository, never()).findById(any(BigInteger.class));
        verify(sessionRepository, never()).save(any(Session.class));
    }

}