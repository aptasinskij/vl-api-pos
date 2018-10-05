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
public class CustomerIdPhoneTest {

    @Autowired
    private JacksonTester<CustomerIdPhone> json;

    @Test
    public void testSerialization() throws IOException {
        CustomerIdPhone customerIdPhone = new CustomerIdPhone();
        customerIdPhone.setPhone("phone_number");
        customerIdPhone.setCustomerId("customer_id");
        assertThat(json.write(customerIdPhone)).isEqualToJson("customerIdPhone.json");
    }

}