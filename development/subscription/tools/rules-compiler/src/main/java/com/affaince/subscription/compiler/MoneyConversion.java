package com.affaince.subscription.compiler;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by rbsavaliya on 01-05-2016.
 */
public class MoneyConversion {
    private String moneyConvExprName;
    private int currencyValue;
    private float pointValue;

    @JsonProperty ("MoneyConvExprName")
    public String getMoneyConvExprName() {
        return moneyConvExprName;
    }

    public void setMoneyConvExprName(String moneyConvExprName) {
        this.moneyConvExprName = moneyConvExprName;
    }

    @JsonProperty ("CurrencyValue")
    public int getCurrencyValue() {
        return currencyValue;
    }

    public void setCurrencyValue(int currencyValue) {
        this.currencyValue = currencyValue;
    }

    @JsonProperty ("PointValue")
    public float getPointValue() {
        return pointValue;
    }

    public void setPointValue(float pointValue) {
        this.pointValue = pointValue;
    }
}
