package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.CashDevicesStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
@RunWith(SpringRunner.class)
public class CashDevicesStatusTest {

    @Autowired
    private JacksonTester<CashDevicesStatus> json;

    @Test
    public void testDeserialization() throws IOException {
        CashDevicesStatus cashDevicesStatus = new CashDevicesStatus();
        cashDevicesStatus.setAcceptorEnabled(true);
        cashDevicesStatus.setDispenserEnabled(true);
        cashDevicesStatus.setRecyclerEnabled(true);
        cashDevicesStatus.setActualAmount(BigDecimal.valueOf(100));
        cashDevicesStatus.setRequestedAmount(BigDecimal.valueOf(50));
        cashDevicesStatus.setErrorCode("code");
        cashDevicesStatus.setStatus("status");
        assertThat(json.write(cashDevicesStatus)).isEqualToJson("cashDevicesStatus.json");
    }

}