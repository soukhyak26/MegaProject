package com.affaince.subscription.business.vo;

import com.affaince.subscription.common.type.QuantityUnit;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Currency;

/**
 * Created by rbsavaliya on 15-01-2016.
 */
public class RangeRule {
    private String ruleHeader;
    private double ruleMinimum;
    private double ruleMaximum;
    private QuantityUnit ruleUnit;
    private double applicableValue;
    @Autowired
    private Currency currency;


    public String getRuleHeader() {
        return ruleHeader;
    }

    public void setRuleHeader(String ruleHeader) {
        this.ruleHeader = ruleHeader;
    }

    public double getRuleMinimum() {
        return ruleMinimum;
    }

    public void setRuleMinimum(double ruleMinimum) {
        this.ruleMinimum = ruleMinimum;
    }

    public double getRuleMaximum() {
        return ruleMaximum;
    }

    public void setRuleMaximum(double ruleMaximum) {
        this.ruleMaximum = ruleMaximum;
    }

    public double getApplicableValue() {
        return applicableValue;
    }

    public void setApplicableValue(double applicableValue) {
        this.applicableValue = applicableValue;
    }

    public QuantityUnit getRuleUnit() {
        return ruleUnit;
    }

    public void setRuleUnit(QuantityUnit ruleUnit) {
        this.ruleUnit = ruleUnit;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RangeRule)) return false;

        RangeRule rangeRule = (RangeRule) o;

        if (Double.compare(rangeRule.ruleMinimum, ruleMinimum) != 0) return false;
        return Double.compare(rangeRule.ruleMaximum, ruleMaximum) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(ruleMinimum);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ruleMaximum);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
