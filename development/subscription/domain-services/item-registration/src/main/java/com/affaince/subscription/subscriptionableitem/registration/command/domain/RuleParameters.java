package com.affaince.subscription.subscriptionableitem.registration.command.domain;

import com.affaince.subscription.common.type.Discount;
import com.affaince.subscription.common.type.Period;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
class RuleParameters {

    private Discount minPermissibleDiscount;
    private Discount maxPermissibleDiscount;
    private short maxPermissibleSubscriptionPeriod;
    private Period maxPermissibleSubscriptionPeriodUnit;

    public RuleParameters(Discount minPermissibleDiscount, Discount maxPermissibleDiscount, short maxPermissibleSubscriptionPeriod, Period maxPermissibleSubscriptionPeriodUnit) {
        this.minPermissibleDiscount = minPermissibleDiscount;
        this.maxPermissibleDiscount = maxPermissibleDiscount;
        this.maxPermissibleSubscriptionPeriod = maxPermissibleSubscriptionPeriod;
        this.maxPermissibleSubscriptionPeriodUnit = maxPermissibleSubscriptionPeriodUnit;
    }

    public Discount getMinPermissibleDiscount() {
        return minPermissibleDiscount;
    }

    public void setMinPermissibleDiscount(Discount minPermissibleDiscount) {
        this.minPermissibleDiscount = minPermissibleDiscount;
    }

    public Discount getMaxPermissibleDiscount() {
        return maxPermissibleDiscount;
    }

    public void setMaxPermissibleDiscount(Discount maxPermissibleDiscount) {
        this.maxPermissibleDiscount = maxPermissibleDiscount;
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
