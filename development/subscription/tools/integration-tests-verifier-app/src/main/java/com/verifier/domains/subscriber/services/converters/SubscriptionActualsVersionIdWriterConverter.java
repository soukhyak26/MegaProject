package com.verifier.domains.subscriber.services.converters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.verifier.domains.subscriber.vo.SubscriptionActualsVersionId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * Created by mandark on 28-01-2016.
 */
@WritingConverter
public class SubscriptionActualsVersionIdWriterConverter implements Converter<SubscriptionActualsVersionId, DBObject> {

    public DBObject convert(SubscriptionActualsVersionId source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("startDate", source.getStartDate().toString());
        dbo.put("valueRangeMin", source.getValueRangeMin());
        dbo.put("valueRangeMax",source.getValueRangeMax());
        return dbo;
    }
}
