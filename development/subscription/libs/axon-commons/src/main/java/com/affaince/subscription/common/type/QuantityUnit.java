package com.affaince.subscription.common.type;

import com.affaince.subscription.common.deserializer.QuantityUnitDeserializer;
import com.affaince.subscription.common.serializer.QuantityUnitSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by mandark on 02-01-2016.
 */
@JsonSerialize(using = QuantityUnitSerializer.class)
@JsonDeserialize(using = QuantityUnitDeserializer.class)
public enum QuantityUnit {
    GM("gram"), KG("kilogram"), LT("Litre"), ml("millilitre");
    private String unitName;
    private QuantityUnit(String name) {
        this.unitName = name;
    }

    public String getUnitName() {
        return unitName;
    }
}
