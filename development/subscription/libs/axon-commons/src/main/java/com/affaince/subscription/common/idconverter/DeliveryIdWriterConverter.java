package com.affaince.subscription.common.idconverter;

import com.affaince.subscription.common.vo.DeliveryId;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by rsavaliya on 14/4/17.
 */
public class DeliveryIdWriterConverter implements Converter<DeliveryId, DBObject> {
    @Override
    public DBObject convert(DeliveryId deliveryId) {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("deliveryId", deliveryId.getDeliveryId());
        dbObject.put("subscriberId", deliveryId.getSubscriberId());
        dbObject.put("subscriptionId", deliveryId.getSubscriberId());
        return dbObject;
    }
}
