package com.affaince.subscription.common.type;

/**
 * Created by rbsavaliya on 29-08-2015.
 */
public class Discount {

    private float value;
    private DiscountUnit unit;

    public Discount(float value, DiscountUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Discount() {
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public DiscountUnit getUnit() {
        return unit;
    }

    public void setUnit(DiscountUnit unit) {
        this.unit = unit;
    }
}
