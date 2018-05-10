package com.verifier.domains.subscriber.services.converters;

import com.affaince.subscription.common.vo.DeliveryForecastVersionId;
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
        double weightRangeMin = (Double) o.get("weightRangeMin");
        double weightRangeMax=(Double) o.get("weightRangeMax");

        DeliveryForecastVersionId id = new DeliveryForecastVersionId(LocalDate.parse(deliveryDate),LocalDate.parse(forecastDate), weightRangeMin,weightRangeMax);
        return id;
    }

}
