package com.affiance.prediction.config;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.joda.time.LocalDate;

public class DateTimeModule extends SimpleModule {

    public DateTimeModule() {
        super();
        addSerializer(LocalDate.class, new LocalDateSerializer());
        addDeserializer(LocalDate.class, new LocalDateDeserializer());
    }
}