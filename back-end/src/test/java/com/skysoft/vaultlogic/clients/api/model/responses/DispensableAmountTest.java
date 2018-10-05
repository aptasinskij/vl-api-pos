package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.DispensableAmount;
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
public class DispensableAmountTest {

    @Autowired
    private JacksonTester<DispensableAmount> json;

    @Test
    public void testDeserializationAvailableAmount() throws IOException {
        DispensableAmount availableAmount = new DispensableAmount();
        availableAmount.setAmount(BigDecimal.valueOf(100));
        availableAmount.setErrorCode("code");
        availableAmount.setStatus("status");
        assertThat(json.read("dispensableAvailableAmount.json")).isEqualTo(availableAmount);
    }

    @Test
    public void testDeserializationRequestedAmount() throws IOException {
        DispensableAmount requestedAmount = new DispensableAmount();
        requestedAmount.setAmount(BigDecimal.valueOf(50));
        requestedAmount.setErrorCode("code");
        requestedAmount.setStatus("status");
        assertThat(json.read("dispensableRequestedAmount.json")).isEqualTo(requestedAmount);
    }

}