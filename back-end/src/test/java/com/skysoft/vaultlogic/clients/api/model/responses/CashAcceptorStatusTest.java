package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.CashAcceptorStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


@JsonTest
@RunWith(SpringRunner.class)
public class CashAcceptorStatusTest {

    @Autowired
    private JacksonTester<CashAcceptorStatus> json;

    @Test
    public void testSerialization() throws IOException {

    }

}