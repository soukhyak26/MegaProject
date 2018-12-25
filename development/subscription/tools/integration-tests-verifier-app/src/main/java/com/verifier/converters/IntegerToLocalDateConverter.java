package com.verifier.converters;

import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

public class IntegerToLocalDateConverter implements Converter<Long, LocalDate> {
    public IntegerToLocalDateConverter() {
    }

    public LocalDate convert(Long source) {
        return source == null ? null : new LocalDate(source);
    }
}
