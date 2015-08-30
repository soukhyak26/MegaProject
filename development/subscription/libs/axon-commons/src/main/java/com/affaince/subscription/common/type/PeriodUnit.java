package com.affaince.subscription.common.type;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public enum PeriodUnit {

    WEEK(0), MONTH(1);

    private int periodCode;

    PeriodUnit(int periodCode) {
        this.periodCode = periodCode;
    }

    public int getPeriodCode() {
        return periodCode;
    }

    public static PeriodUnit valueOf (int periodCode) {
        switch (periodCode) {
            case 0:
                return WEEK;
            case 1:
                return MONTH;
            default:
                return MONTH;
        }
    }
}
