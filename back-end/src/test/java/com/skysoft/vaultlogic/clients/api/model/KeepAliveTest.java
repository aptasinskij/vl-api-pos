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
public class KeepAliveTest {

    @Autowired
    private JacksonTester<KeepAlive> json;

    @Test
    public void testSerialization() throws IOException {
        KeepAlive keepAlive = KeepAlive.of("maya_keepalive_token");
        assertThat(json.write(keepAlive)).isEqualToJson("keepAlive.json");
    }

}