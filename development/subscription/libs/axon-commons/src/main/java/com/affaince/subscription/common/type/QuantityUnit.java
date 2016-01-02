package com.affaince.subscription.common.type;

/**
 * Created by mandark on 02-01-2016.
 */
public enum QuantityUnit {

    GM("gram"), KG("kilogram"), LT("Litre"), ml("millilitre");

    private String unitName;

    private QuantityUnit(String name) {
        this.unitName = name;
    }
}
