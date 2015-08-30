package com.affaince.subscription.common.type;

/**
 * Created by rbsavaliya on 29-08-2015.
 */
public class Frequency {

    private int value;
    private Period period;

    public Frequency(int value, Period period) {
        this.value = value;
        this.period = period;
    }

    public Frequency() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
