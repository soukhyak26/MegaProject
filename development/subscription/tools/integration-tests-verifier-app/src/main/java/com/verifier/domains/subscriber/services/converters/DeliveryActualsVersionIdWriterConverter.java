package com.verifier.domains.subscriber.services.converters;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.verifier.domains.subscriber.vo.DeliveryActualsVersionId;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by mandar on 11/7/2017.
 */
public class DeliveryActualsVersionIdWriterConverter implements Converter<DeliveryActualsVersionId, DBObject> {
    public DBObject convert(DeliveryActualsVersionId source) {
        DBObject dbo = new BasicDBObject();
        dbo.put("deliveryDate", source.getDeliveryDate().toString());
        dbo.put("weightRangeMin", source.getWeightRangeMin());
        dbo.put("weightRangeMax",source.getWeightRangeMax());
        return dbo;
    }

}
