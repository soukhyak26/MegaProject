package com.verifier.converters;

import com.mongodb.BasicDBObject;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by mandar on 02-07-2016.
 */
public class LocalDateWritingConverter implements Converter<LocalDate, BasicDBObject> {
    public BasicDBObject convert(LocalDate date){
        BasicDBObject dbo = new BasicDBObject();
        dbo.put("currentDate", date.toDate());
        return dbo;
    }
}
