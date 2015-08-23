package com.affaince.subscription.consumerbasket.command.domain;

/**
 * Created by rbsavaliya on 23-08-2015.
 */
public class Frequency {
    enum FrequencyUnit {WEEK, MONTH}

    ;
    private int frequency;
    private FrequencyUnit frequencyUnit = FrequencyUnit.MONTH;

    public Frequency(int frequency, String unit) {
        this.frequency = frequency;
        if (unit != null) {
            this.frequencyUnit = FrequencyUnit.valueOf(unit.toUpperCase());
        }
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public FrequencyUnit getFrequencyUnit() {
        return frequencyUnit;
    }

    public void setFrequencyUnit(FrequencyUnit frequencyUnit) {
        this.frequencyUnit = frequencyUnit;
    }
}
