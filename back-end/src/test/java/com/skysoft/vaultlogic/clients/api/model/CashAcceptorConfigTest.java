package com.skysoft.vaultlogic.clients.api.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
@RunWith(SpringRunner.class)
public class CashAcceptorConfigTest {

    @Autowired
    private JacksonTester<CashAcceptorConfig> json;

    @Test
    public void testSerialization() throws IOException {
        CashAcceptorConfig cashAcceptorConfig = CashAcceptorConfig.onlyTwentiesOf(BigInteger.valueOf(100L));
        assertThat(json.write(cashAcceptorConfig)).isEqualToJson("cashAcceptorConfig.json");
    }

}