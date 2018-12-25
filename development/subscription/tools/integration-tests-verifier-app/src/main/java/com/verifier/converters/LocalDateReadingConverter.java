package com.verifier.converters;

import com.mongodb.BasicDBObject;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Created by mandar on 02-07-2016.
 */
@ReadingConverter
public class LocalDateReadingConverter implements Converter<BasicDBObject, LocalDate> {

    @Override
    public LocalDate convert(BasicDBObject o) {

        return new LocalDate(o.getDate("currentDate"));
    }
}
