package com.affaince.subscription.subscriptionableitem.registration.command.domain;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
class RuleParameters {

    private short minPermissibleDiscount;
    private short maxPermissibleDiscount;
    private short maxPermissibleUnits;
    private short maxPermissibleSubscriptionPeriod;
    private Period maxPermissibleSubscriptionPeriodUnit;

    public RuleParameters(short minPermissibleDiscount, short maxPermissibleDiscount, short maxPermissibleUnits, short maxPermissibleSubscriptionPeriod, String maxPermissibleSubscriptionPeriodUnit) {
        this.minPermissibleDiscount = minPermissibleDiscount;
        this.maxPermissibleDiscount = maxPermissibleDiscount;
        this.maxPermissibleUnits = maxPermissibleUnits;
        this.maxPermissibleSubscriptionPeriod = maxPermissibleSubscriptionPeriod;
        this.maxPermissibleSubscriptionPeriodUnit = Period.valueOf(maxPermissibleSubscriptionPeriodUnit.toUpperCase());
    }

    public short getMinPermissibleDiscount() {
        return minPermissibleDiscount;
    }

    public void setMinPermissibleDiscount(short minPermissibleDiscount) {
        this.minPermissibleDiscount = minPermissibleDiscount;
    }

    public short getMaxPermissibleDiscount() {
        return maxPermissibleDiscount;
    }

    public void setMaxPermissibleDiscount(short maxPermissibleDiscount) {
        this.maxPermissibleDiscount = maxPermissibleDiscount;
    }

    public short getMaxPermissibleUnits() {
        return maxPermissibleUnits;
    }

    public void setMaxPermissibleUnits(short maxPermissibleUnits) {
        this.maxPermissibleUnits = maxPermissibleUnits;
    }

    public short getMaxPermissibleSubscriptionPeriod() {
        return maxPermissibleSubscriptionPeriod;
    }

    public void setMaxPermissibleSubscriptionPeriod(short maxPermissibleSubscriptionPeriod) {
        this.maxPermissibleSubscriptionPeriod = maxPermissibleSubscriptionPeriod;
    }

    public Period getMaxPermissibleSubscriptionPeriodUnit() {
        return maxPermissibleSubscriptionPeriodUnit;
    }

    public void setMaxPermissibleSubscriptionPeriodUnit(Period maxPermissibleSubscriptionPeriodUnit) {
        this.maxPermissibleSubscriptionPeriodUnit = maxPermissibleSubscriptionPeriodUnit;
    }
}
