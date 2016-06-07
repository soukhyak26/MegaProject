package com.affaince.subscription.compiler;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by rbsavaliya on 01-05-2016.
 */
public class PeriodConversion {

    private String periodConvExprName;
    private int periodValue;
    private int pointValue;

    @JsonProperty ("PeriodConvExprName")
    public String getPeriodConvExprName() {
        return periodConvExprName;
    }

    public void setPeriodConvExprName(String periodConvExprName) {
        this.periodConvExprName = periodConvExprName;
    }


    @JsonProperty ("PeriodValue")
    public int getPeriodValue() {
        return periodValue;
    }

    public void setPeriodValue(int periodValue) {
        this.periodValue = periodValue;
    }

    @JsonProperty ("PointValue")
    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }
}
