package com.skysoft.vaultlogic.clients.api.model;

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
public class CustomerPhoneTest {

    @Autowired
    private JacksonTester<CustomerPhone> json;

    @Test
    public void testSerialization() throws IOException {
        CustomerPhone customerPhone = CustomerPhone.of("phone_number");
        assertThat(json.write(customerPhone)).isEqualToJson("customerPhone.json");
    }

}