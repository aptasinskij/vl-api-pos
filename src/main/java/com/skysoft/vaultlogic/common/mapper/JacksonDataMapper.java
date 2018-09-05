package com.skysoft.vaultlogic.common.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class JacksonDataMapper implements DataMapper {

    private final ObjectMapper objectMapper;

    @Autowired
    public JacksonDataMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T convertValue(Object object, Class<T> dataType) {
        try {
            return objectMapper.convertValue(object, dataType);
        } catch (IllegalArgumentException ex) {
            log.warn("Error during object conversion: {}", ex.getMessage());
            throw new DataMapperException("Can't convert object", ex);
        }
    }

    @Override
    public <T> T convert(String data, TypeReference<?> dataType) {
        try {
            return objectMapper.readValue(data, dataType);
        } catch (IOException e) {
            log.warn("Error during string conversion, {}", e.getMessage());
            throw new DataMapperException("Can't read string", e);
        }
    }

    @Override
    public String objectToString(Object data) {
        try {
            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            log.warn("Error during string creation, {}", e.getMessage());
            throw new DataMapperException("Can`t create string", e);
        }
    }

}
