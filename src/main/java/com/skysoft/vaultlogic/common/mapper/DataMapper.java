package com.skysoft.vaultlogic.common.mapper;

import com.fasterxml.jackson.core.type.TypeReference;

public interface DataMapper {

    <T> T convertValue(Object object, Class<T> dataType);

    <T> T convert(String data, TypeReference<?> dataType);

    String objectToString(Object data);

}
