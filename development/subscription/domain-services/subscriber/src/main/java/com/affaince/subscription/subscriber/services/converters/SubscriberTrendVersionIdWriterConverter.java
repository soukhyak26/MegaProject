package com.affaince.subscription.subscriber.services.converters;

import com.affaince.subscription.subscriber.vo.SubscriberTrendVersionId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * Created by mandark on 28-01-2016.
 */
@WritingConverter
public class SubscriberTrendVersionIdWriterConverter implements Converter<SubscriberTrendVersionId, DBObject> {

    public DBObject convert(SubscriberTrendVersionId source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("trendSettingDate", source.getTrendSettingDate().toString());
        dbo.put("startDate", source.getStartDate().toString());
        return dbo;
    }
}
