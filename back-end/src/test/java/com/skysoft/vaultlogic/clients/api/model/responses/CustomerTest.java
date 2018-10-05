package com.skysoft.vaultlogic.clients.api.model.responses;

import com.skysoft.vaultlogic.clients.api.model.Customer;
import com.skysoft.vaultlogic.clients.api.model.StatusCode;
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
public class CustomerTest {

    @Autowired
    private JacksonTester<Customer> json;

    @Test
    public void testDeserialization() throws IOException {
        Customer customer = new Customer();
        customer.setPhone("customer_phone");
        customer.setCustomerId("customer_id");
        customer.setFirstName("first_name");
        customer.setMiddleName("middle_name");
        customer.setLastName("last_name");
        customer.setEmail("email");
        customer.setErrorCode("code");
        customer.setStatus("status");
        assertThat(json.write(customer)).isEqualToJson("customer.json");
    }

}