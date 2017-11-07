package com.affaince.subscription.subscriber.services.converters;

import com.affaince.subscription.subscriber.vo.DeliveryForecastVersionId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by mandar on 11/7/2017.
 */
public class DeliveryForecastVersionIdWriterConverter implements Converter<DeliveryForecastVersionId, DBObject> {
    public DBObject convert(DeliveryForecastVersionId source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("forecastDate", source.getForecastDate().toString());
        dbo.put("deliveryDate", source.getDeliveryDate().toString());
        dbo.put("weightRangeMin", source.getWeightRangeMin());
        dbo.put("weightRangeMax",source.getWeightRangeMax());
        return dbo;
    }

}
