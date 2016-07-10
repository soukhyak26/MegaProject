package com.affaince.subscription.common.type;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public enum PeriodUnit {

    DAY(0),WEEK(1), MONTH(2), YEAR(3);

    private int periodCode;

    PeriodUnit(int periodCode) {
        this.periodCode = periodCode;
    }

    public static PeriodUnit valueOf(int periodCode) {
        switch (periodCode) {
            case 0:
                return DAY;
            case 1:
                return WEEK;
            case 2:
                return MONTH;
            case 3:
                return YEAR;
            default:
                return MONTH;
        }
    }

    public int getPeriodCode() {
        return periodCode;
    }
}
