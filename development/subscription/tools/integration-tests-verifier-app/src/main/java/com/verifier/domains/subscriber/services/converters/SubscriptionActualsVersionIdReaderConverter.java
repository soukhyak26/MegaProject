package com.verifier.domains.subscriber.services.converters;

import com.mongodb.DBObject;
import com.verifier.domains.subscriber.vo.SubscriptionActualsVersionId;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

/**
 * Created by mandark on 28-01-2016.
 */
@ReadingConverter
public class SubscriptionActualsVersionIdReaderConverter implements Converter<DBObject, SubscriptionActualsVersionId> {
    @Override
    public SubscriptionActualsVersionId convert(DBObject o) {

        String startDate = (String) o.get("startDate");
        String weightRangeMin = (String) o.get("valueRangeMin");
        String weightRangeMax=(String) o.get("valueRangeMax");

        SubscriptionActualsVersionId id = new SubscriptionActualsVersionId(LocalDate.parse(startDate), Double.parseDouble(weightRangeMin),Double.parseDouble(weightRangeMax));
        return id;
    }
}
