package com.skysoft.vaultlogic.services.ganache;

import com.skysoft.vaultlogic.common.domain.application.Application;
import com.skysoft.vaultlogic.common.domain.application.ApplicationRepository;
import com.skysoft.vaultlogic.common.domain.kiosk.Kiosk;
import com.skysoft.vaultlogic.common.domain.session.Session;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.user.User;
import com.skysoft.vaultlogic.services.KioskService;
import com.skysoft.vaultlogic.services.SessionService;
import com.skysoft.vaultlogic.services.local.LocalSessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.util.Pair;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.Optional;

import static java.math.BigInteger.ONE;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class LocalSessionServiceTest {

    @TestConfiguration
    static class GanacheSessionServiceTestConfiguration {

        @MockBean
        private SessionRepository sessionRepository;

        @MockBean
        private ApplicationRepository applicationRepository;

        @MockBean
        private KioskService kioskService;

        @Bean
        public SessionService sessionService() {
            return new LocalSessionService(sessionRepository, applicationRepository, kioskService);
        }

    }

    @Autowired
    private KioskService kioskService;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SessionService sessionService;

    private Session session = mock(Session.class);
    private User user = mock(User.class);
    private Application application = mock(Application.class);
    private Kiosk kiosk = mock(Kiosk.class);

    @Test(expected = RuntimeException.class)
    public void givenFailToRetrieveKiosk_whenException_thanCorrect() {
        when(kioskService.resolveKioskForSession(anyString())).thenReturn(Optional.ofNullable(null));
        sessionService.createSession(BigInteger.ZERO, "xToken");
    }

    @Test(expected = RuntimeException.class)
    public void givenApplicationNotExists_whenException_thanCorrect() {
        when(kioskService.resolveKioskForSession(anyString())).thenReturn(Optional.of(kiosk));
        when(applicationRepository.findById(any(BigInteger.class))).thenReturn(Optional.ofNullable(null));
        sessionService.createSession(BigInteger.ZERO, "xToken");
    }

    @Test
    public void givenApplicationAndKioskFound_whenSessionCreated_thanCorrect() {
        String localhost = "http://localhost.com";
        when(application.getUri()).thenReturn(localhost);
        when(session.getId()).thenReturn(ONE);
        when(session.getId()).thenReturn(ONE);
        when(sessionRepository.save(any())).thenReturn(session);
        when(kioskService.resolveKioskForSession(anyString())).thenReturn(Optional.of(kiosk));
        when(applicationRepository.findById(any(BigInteger.class))).thenReturn(Optional.of(application));

        Pair<String, BigInteger> appUrlSessionId = sessionService.createSession(ONE, "bla");
        assertEquals(localhost, appUrlSessionId.getFirst());
        assertEquals(ONE, appUrlSessionId.getSecond());

        verify(sessionRepository, times(1)).save(any());
        verify(kioskService, times(1)).resolveKioskForSession(anyString());
        verify(applicationRepository, times(1)).findById(any(BigInteger.class));

    }

}