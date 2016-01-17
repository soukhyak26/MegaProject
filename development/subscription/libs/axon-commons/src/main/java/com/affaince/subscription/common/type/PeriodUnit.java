package com.affaince.subscription.common.type;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public enum PeriodUnit {

    WEEK(0), MONTH(1), YEAR(2);

    private int periodCode;

    PeriodUnit(int periodCode) {
        this.periodCode = periodCode;
    }

    public static PeriodUnit valueOf(int periodCode) {
        switch (periodCode) {
            case 0:
                return WEEK;
            case 1:
                return MONTH;
            case 2:
                return YEAR;
            default:
                return MONTH;
        }
    }

    public int getPeriodCode() {
        return periodCode;
    }
}
