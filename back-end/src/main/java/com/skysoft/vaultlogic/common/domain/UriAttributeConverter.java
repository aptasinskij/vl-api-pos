package com.skysoft.vaultlogic.common.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.net.URI;

@Converter(autoApply = true)
public class UriAttributeConverter implements AttributeConverter<URI, String> {

    @Override
    public String convertToDatabaseColumn(URI attribute) {
        return attribute.toString();
    }

    @Override
    public URI convertToEntityAttribute(String dbData) {
        return URI.create(dbData);
    }

}
