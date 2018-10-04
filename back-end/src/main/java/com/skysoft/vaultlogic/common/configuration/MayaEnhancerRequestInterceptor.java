package com.skysoft.vaultlogic.common.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedHashMap;

import static java.lang.String.valueOf;

@Component
public class MayaEnhancerRequestInterceptor implements ClientHttpRequestInterceptor {

    private final ObjectMapper objectMapper;

    @Autowired
    public MayaEnhancerRequestInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        LinkedHashMap map = objectMapper.readValue(body, LinkedHashMap.class);
//        map.put(getxNonceHeader(), valueOf(System.currentTimeMillis()));
        return execution.execute(request, objectMapper.writeValueAsBytes(map));
    }

}
