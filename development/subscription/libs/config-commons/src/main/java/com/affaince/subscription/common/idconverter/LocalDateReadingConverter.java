package com.affaince.subscription.common.idconverter;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.mongodb.DBObject;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Created by mandar on 02-07-2016.
 */
@ReadingConverter
public class LocalDateReadingConverter implements Converter<DBObject, LocalDate> {

    @Override
    public LocalDate convert(DBObject o) {
        String endDate = (String) o.get("endDate");
        return LocalDate.parse(endDate);
    }
}
