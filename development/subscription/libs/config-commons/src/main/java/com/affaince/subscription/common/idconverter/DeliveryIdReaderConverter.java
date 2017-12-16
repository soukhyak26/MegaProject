package com.affaince.subscription.common.idconverter;

import com.affaince.subscription.common.vo.DeliveryId;
import com.mongodb.DBObject;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by rsavaliya on 14/4/17.
 */
public class DeliveryIdReaderConverter implements Converter<DBObject, DeliveryId> {
    @Override
    public DeliveryId convert(DBObject dbObject) {
        String deliveryId = (String) dbObject.get("deliveryId");
        String subscriberId = (String) dbObject.get ("subscriberId");
        String subscriptionId = (String) dbObject.get ("subscriptionId");
        return new DeliveryId(deliveryId, subscriberId, subscriptionId);
    }
}
