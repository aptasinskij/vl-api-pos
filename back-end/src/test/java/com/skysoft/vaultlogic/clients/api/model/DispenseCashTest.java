package com.skysoft.vaultlogic.clients.api.model;

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
public class DispenseCashTest {

    @Autowired
    private JacksonTester<DispenseCash> json;

    @Test
    public void testSerialization() throws IOException {
        DispenseCash dispensableAmount = new DispenseCash();
        dispensableAmount.setAmount(BigDecimal.valueOf(100));
        assertThat(json.write(dispensableAmount)).isEqualToJson("dispenseCash.json");
    }

}