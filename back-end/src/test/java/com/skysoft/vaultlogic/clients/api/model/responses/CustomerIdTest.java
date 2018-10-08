package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.CustomerId;
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
public class CustomerIdTest {

    @Autowired
    private JacksonTester<CustomerId> json;

    @Test
    public void testDeserialization() throws IOException {
        CustomerId customerId = new CustomerId();
        customerId.setCustomerId("customer_id");
        customerId.setErrorCode("code");
        customerId.setStatus("status");
        assertThat(json.read("customerId.json")).isEqualTo(customerId);
    }

}