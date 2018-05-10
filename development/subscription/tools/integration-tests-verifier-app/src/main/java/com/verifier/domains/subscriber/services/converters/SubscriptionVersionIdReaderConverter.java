package com.verifier.domains.subscriber.services.converters;

import com.mongodb.DBObject;
import com.verifier.domains.subscriber.vo.SubscriptionVersionId;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Created by mandark on 28-01-2016.
 */
@ReadingConverter
public class SubscriptionVersionIdReaderConverter implements Converter<DBObject, SubscriptionVersionId> {
    @Override
    public SubscriptionVersionId convert(DBObject o) {

        String forecastDate = (String) o.get("forecastDate");
        String startDate = (String) o.get("startDate");
        double weightRangeMin =  (Double)o.get("weightRangeMin");
        double weightRangeMax= (Double)o.get("weightRangeMax");

        SubscriptionVersionId id = new SubscriptionVersionId(LocalDate.parse(startDate), LocalDate.parse(forecastDate),weightRangeMin,weightRangeMax);
        return id;
    }
}
