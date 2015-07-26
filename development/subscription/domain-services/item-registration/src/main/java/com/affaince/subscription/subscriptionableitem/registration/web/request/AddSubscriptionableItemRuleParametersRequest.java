package com.affaince.subscription.subscriptionableitem.registration.web.request;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class AddSubscriptionableItemRuleParametersRequest {

    private short minPermissibleDiscount;
    private short maxPermissibleDiscount;
    private short maxPermissibleUnits;
    private short maxPermissibleSubscriptionPeriod;
    private String maxPermissibleSubscriptionPeriodUnit;

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

    public String getMaxPermissibleSubscriptionPeriodUnit() {
        return maxPermissibleSubscriptionPeriodUnit;
    }

    public void setMaxPermissibleSubscriptionPeriodUnit(String maxPermissibleSubscriptionPeriodUnit) {
        this.maxPermissibleSubscriptionPeriodUnit = maxPermissibleSubscriptionPeriodUnit;
    }
}
