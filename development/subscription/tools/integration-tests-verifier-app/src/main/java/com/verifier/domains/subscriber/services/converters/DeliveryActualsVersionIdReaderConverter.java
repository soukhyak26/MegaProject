package com.verifier.domains.subscriber.services.converters;

import com.mongodb.DBObject;
import com.verifier.domains.subscriber.vo.DeliveryActualsVersionId;
import org.joda.time.LocalDate;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by mandar on 11/7/2017.
 */
public class DeliveryActualsVersionIdReaderConverter implements Converter<DBObject, DeliveryActualsVersionId> {
    @Override
    public DeliveryActualsVersionId convert(DBObject o) {

        String deliveryDate = (String) o.get("deliveryDate");
        double weightRangeMin = (Double) o.get("weightRangeMin");
        double weightRangeMax=(Double) o.get("weightRangeMax");

        DeliveryActualsVersionId id = new DeliveryActualsVersionId(LocalDate.parse(deliveryDate), weightRangeMin,weightRangeMax);
        return id;
    }

}
