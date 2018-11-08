package com.skysoft.vaultlogic.observers.cloud;

import com.skysoft.vaultlogic.clients.api.KioskCashDevices;
import com.skysoft.vaultlogic.clients.api.KioskDevicesClient;
import com.skysoft.vaultlogic.clients.api.model.Cassette;
import com.skysoft.vaultlogic.clients.api.model.DispensableAmount;
import com.skysoft.vaultlogic.clients.api.model.KioskDevice;
import com.skysoft.vaultlogic.common.domain.kiosk.mapper.KioskMapper;
import com.skysoft.vaultlogic.common.domain.session.SessionRepository;
import com.skysoft.vaultlogic.common.domain.session.projections.SessionXToken;
import com.skysoft.vaultlogic.contracts.KioskOracle;
import com.skysoft.vaultlogic.contracts.KioskOracle.GetKioskInfoEventResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

import static com.skysoft.vaultlogic.common.domain.kiosk.Kiosk.kiosk;
import static io.vavr.control.Try.success;
import static java.math.BigInteger.valueOf;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GetKioskInfoObserverTest {

    @Mock
    SessionRepository sessionRepository;

    @Mock
    KioskDevicesClient kioskDevicesClient;

    @Mock
    KioskCashDevices kioskCashDevices;

    @Mock
    KioskMapper kioskMapper;

    @Mock
    KioskOracle kioskOracle;

    GetKioskInfoObserver getKioskInfoObserver;

    DispensableAmount dispensableAmount = new DispensableAmount();

    Cassette firstCassette = new Cassette();
    Cassette secondCassette = new Cassette();

    GetKioskInfoEventResponse event = new GetKioskInfoEventResponse();
    TransactionReceipt transactionReceipt = new TransactionReceipt();

    @Before
    public void setUp() {
        getKioskInfoObserver = new GetKioskInfoObserver(kioskOracle, sessionRepository, kioskDevicesClient, kioskMapper, kioskCashDevices);
        event._commandId = BigInteger.ONE;
        event._sessionId = BigInteger.ONE;
        firstCassette.setDenomination(valueOf(100));
        firstCassette.setAmount(valueOf(200));
        secondCassette.setDenomination(valueOf(50));
        secondCassette.setAmount(valueOf(20));
        dispensableAmount.setCassettes(asList(firstCassette, secondCassette));
        when(sessionRepository.findSessionXTokenById(any(BigInteger.class))).thenReturn(new SessionXToken() {
            @Override
            public String getxToken() {
                return "abc";
            }
        });
        when(kioskDevicesClient.getKioskInfo("abc")).thenReturn(success(new KioskDevice()));
        when(kioskMapper.fromKioskDevice(any(KioskDevice.class))).thenReturn(kiosk("undefined", "undefined", "undefined", "undefined"));
        when(kioskCashDevices.getDispensableAmount(any(), any())).thenReturn(success(dispensableAmount));
        when(kioskOracle.successGetKioskInfo(any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(new RemoteCall<>(() -> transactionReceipt));
    }

    @Test
    public void whenCorrectOracleResponseThanCorrect() {
        getKioskInfoObserver.onNext(event);
        verify(sessionRepository, times(1)).findSessionXTokenById(BigInteger.ONE);
        verify(kioskDevicesClient, times(1)).getKioskInfo("abc");
        verify(kioskCashDevices, times(1)).getDispensableAmount(any(), any());
        verify(kioskOracle, times(1)).successGetKioskInfo(
                BigInteger.ONE,
                "undefined",
                "undefined",
                "undefined",
                "undefined",
                asList(valueOf(50), valueOf(100)),
                asList(valueOf(20), valueOf(200))
                );
    }


}