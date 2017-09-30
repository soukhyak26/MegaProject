package com.affaince.subscription.subscriber.services.converters;

import com.affaince.subscription.subscriber.vo.DeliveryVersionId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

/**
 * Created by mandark on 28-01-2016.
 */
@WritingConverter
public class DeliveryTrendVersionIdWriterConverter implements Converter<DeliveryVersionId, DBObject> {

    public DBObject convert(DeliveryVersionId source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("trendSettingDate", source.getForecastDate().toString());
        dbo.put("startDate", source.getStartDate().toString());
        dbo.put("weightRangeMin", source.getWeightRangeMin());
        dbo.put("weightRangeMax",source.getWeightRangeMax());
        return dbo;
    }
}
