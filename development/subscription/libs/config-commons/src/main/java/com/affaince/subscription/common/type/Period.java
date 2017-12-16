package com.affaince.subscription.common.type;

/**
 * Created by rbsavaliya on 29-08-2015.
 */
public class Period {
    private int value;
    private PeriodUnit unit;

    public Period(int value, PeriodUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public Period() {
    }

    public PeriodUnit getUnit() {
        return unit;
    }

    public void setUnit(PeriodUnit unit) {
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
