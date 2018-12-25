package com.verifier.converters;

import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

public class LocalDateToIntegerConverter implements Converter<LocalDate, Long> {
    public LocalDateToIntegerConverter() {
    }

    public Long convert(LocalDate source) {
        return source == null ? null : source.toDate().getTime();
    }
}
