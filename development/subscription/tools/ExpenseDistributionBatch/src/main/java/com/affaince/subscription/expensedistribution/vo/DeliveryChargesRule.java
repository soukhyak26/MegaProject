package com.affaince.subscription.expensedistribution.vo;

/**
 * Created by rsavaliya on 13/2/16.
 */
public class DeliveryChargesRule {

    private double ruleMinimum;
    private double ruleMaximum;
    private double applicableValue;

    public DeliveryChargesRule(double ruleMinimum, double ruleMaximum, double applicableValue) {
        this.ruleMinimum = ruleMinimum;
        this.ruleMaximum = ruleMaximum;
        this.applicableValue = applicableValue;
    }

    public double getRuleMinimum() {
        return ruleMinimum;
    }

    public double getRuleMaximum() {
        return ruleMaximum;
    }

    public double getApplicableValue() {
        return applicableValue;
    }
}
