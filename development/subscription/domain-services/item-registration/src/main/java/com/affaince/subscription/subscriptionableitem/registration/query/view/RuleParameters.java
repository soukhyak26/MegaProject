package com.affaince.subscription.subscriptionableitem.registration.query.view;

import com.affaince.subscription.common.type.Discount;
import com.affaince.subscription.common.type.Period;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class RuleParameters {

    private Discount minPermissibleDiscount;
    private Discount maxPermissibleDiscount;
    private short maxPermissibleUnitsPerSubscription;
    private Period maxPermissibleSubscriptionPeriod;

    public RuleParameters(Discount minPermissibleDiscount, Discount maxPermissibleDiscount, short maxPermissibleUnitsPerSubscription, Period maxPermissibleSubscriptionPeriod) {
        this.minPermissibleDiscount = minPermissibleDiscount;
        this.maxPermissibleDiscount = maxPermissibleDiscount;
        this.maxPermissibleUnitsPerSubscription = maxPermissibleUnitsPerSubscription;
        this.maxPermissibleSubscriptionPeriod = maxPermissibleSubscriptionPeriod;
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

    public short getMaxPermissibleUnitsPerSubscription() {
        return maxPermissibleUnitsPerSubscription;
    }

    public void setMaxPermissibleUnitsPerSubscription(short maxPermissibleUnitsPerSubscription) {
        this.maxPermissibleUnitsPerSubscription = maxPermissibleUnitsPerSubscription;
    }

    public Period getMaxPermissibleSubscriptionPeriod() {
        return maxPermissibleSubscriptionPeriod;
    }

    public void setMaxPermissibleSubscriptionPeriod(Period maxPermissibleSubscriptionPeriod) {
        this.maxPermissibleSubscriptionPeriod = maxPermissibleSubscriptionPeriod;
    }
}
