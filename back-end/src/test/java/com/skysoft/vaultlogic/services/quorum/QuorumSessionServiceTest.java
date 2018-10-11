package com.skysoft.vaultlogic.services.quorum;

import com.skysoft.vaultlogic.clients.api.KioskApplicationClient;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.application.ApplicationRepository;
import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.user.User;
import com.skysoft.vaultlogic.services.KioskService;
import io.vavr.control.Try;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Pair;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.Optional;
import java.util.UUID;

import static java.math.BigInteger.ZERO;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

    private StatusCode statusCode = new StatusCode();
    private BigInteger applicationId = BigInteger.ONE;
    private BigInteger sessionId = BigInteger.valueOf(1L);
    private String xToken = UUID.randomUUID().toString();
    private Kiosk kiosk = Kiosk.kiosk("shortId", "address", "name", "timezone");
    private User user = User.newUser("username", "secret", "0x0");
    private Application application = Application.newApplication("test", "http", user, "0x0");
    private Session session = Mockito.mock(Session.class);

    @Test(expected = RuntimeException.class)
    public void givenFailedToLaunchApplication_whenError_thanCorrect() {
        when(applicationRepository.findById(applicationId)).thenReturn(Optional.of(application));
        when(kioskService.resolveKioskForSession(xToken)).thenReturn(Optional.of(kiosk));
        when(kioskApplicationClient.launchApplication(anyString())).thenReturn(Try.failure(new RuntimeException()));
        quorumSessionService.createSession(ZERO, "xToken");
        verify(kioskService, never()).resolveKioskForSession(anyString());
        verify(applicationRepository, never()).findById(any(BigInteger.class));
        verify(sessionRepository, never()).save(any(Session.class));
    }

    @Test(expected = RuntimeException.class)
    public void givenKioskNotFound_whenError_thanCorrect() {
        StatusCode statusCode = new StatusCode();
        when(kioskApplicationClient.launchApplication(anyString())).thenReturn(Try.success(statusCode));
        when(kioskService.resolveKioskForSession(anyString())).thenReturn(Optional.empty());
        quorumSessionService.createSession(ZERO, "xToken");
        verify(kioskApplicationClient, times(1)).launchApplication(anyString());
        verify(kioskService, timeout(1)).resolveKioskForSession(anyString());
        verify(sessionRepository, never()).save(any(Session.class));
        verify(applicationRepository, never()).findById(any(BigInteger.class));
    }

    @Test(expected = RuntimeException.class)
    public void givenApplicationNotFound_whenError_thanCorrect() {
        BigInteger applicationId = BigInteger.ONE;
        when(applicationRepository.findById(applicationId)).thenReturn(Optional.empty());
        quorumSessionService.createSession(applicationId, xToken);
        verify(applicationRepository, times(1)).findById(applicationId);
        verify(kioskApplicationClient, never()).launchApplication(xToken);
        verify(kioskService, never()).resolveKioskForSession(xToken);
        verify(sessionRepository, never()).save(any(Session.class));
    }

    @Test
    public void givenApplicationLaunchedKioskResolved_whenSuccess_thanCorrect() {

        when(session.getId()).thenReturn(sessionId);
        when(session.getApplication()).thenReturn(application);
        when(kioskApplicationClient.launchApplication(anyString())).thenReturn(Try.success(statusCode));
        when(kioskService.resolveKioskForSession(anyString())).thenReturn(Optional.of(kiosk));
        when(applicationRepository.findById(applicationId)).thenReturn(Optional.of(application));
        when(sessionRepository.save(any(Session.class))).thenReturn(session);

        Pair<String, BigInteger> stringBigIntegerPair = quorumSessionService.createSession(applicationId, xToken);

        verify(kioskApplicationClient, times(1)).launchApplication(xToken);
        verify(kioskService, times(1)).resolveKioskForSession(xToken);
        verify(applicationRepository, times(1)).findById(applicationId);
        verify(sessionRepository, times(1)).save(any(Session.class));

        assertEquals(application.getUri(), stringBigIntegerPair.getFirst());
        assertEquals(session.getId(), stringBigIntegerPair.getSecond());
    }

}