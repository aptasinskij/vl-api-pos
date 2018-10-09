package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.CashAcceptorStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
@RunWith(SpringRunner.class)
public class CashAcceptorStatusTest {

    @Autowired
    private JacksonTester<CashAcceptorStatus> json;

    @Test
    public void testDeserializationDisableCashAcceptorStatus() throws IOException {
        CashAcceptorStatus cashAcceptorStatus = new CashAcceptorStatus();
        cashAcceptorStatus.setAcceptorStatus(true);
        cashAcceptorStatus.setStatus("status");
        cashAcceptorStatus.setErrorCode("code");
        assertThat(json.read("disableCashAcceptorStatus.json")).isEqualTo(cashAcceptorStatus);
    }

    @Test
    public void testDeserializationEnableCashAcceptorStatus() throws IOException {
        CashAcceptorStatus cashAcceptorStatus = new CashAcceptorStatus();
        cashAcceptorStatus.setAcceptorStatus(false);
        cashAcceptorStatus.setStatus("status");
        cashAcceptorStatus.setErrorCode("code");
        assertThat(json.read("enableCashAcceptorStatus.json")).isEqualTo(cashAcceptorStatus);
    }

}