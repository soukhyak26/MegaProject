package com.affaince.subscription.subscriber.services.converters;

import com.affaince.subscription.subscriber.vo.DeliveryForecastVersionId;
import com.mongodb.DBObject;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by mandar on 11/7/2017.
 */
public class DeliveryForecastVersionIdReaderConverter implements Converter<DBObject, DeliveryForecastVersionId> {
    @Override
    public DeliveryForecastVersionId convert(DBObject o) {

        String forecastDate = (String) o.get("forecastDate");
        String deliveryDate = (String) o.get("deliveryDate");
        String weightRangeMin = (String) o.get("weightRangeMin");
        String weightRangeMax=(String) o.get("weightRangeMax");

        DeliveryForecastVersionId id = new DeliveryForecastVersionId(LocalDate.parse(forecastDate),LocalDate.parse(deliveryDate), Double.parseDouble(weightRangeMin),Double.parseDouble(weightRangeMax));
        return id;
    }

}
