package com.affaince.subscription.subscriptionableitem.registration.command.event;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class AddSubscriptionableItemRuleParametersEvent {
    private String itemId;
    private short minPermissibleDiscount;
    private short maxPermissibleDiscount;
    private short maxPermissibleUnits;
    private short maxPermissibleSubscriptionPeriod;
    private String maxPermissibleSubscriptionPeriodUnit;

    public AddSubscriptionableItemRuleParametersEvent(String itemId, short minPermissibleDiscount, short maxPermissibleDiscount, short maxPermissibleUnits, short maxPermissibleSubscriptionPeriod, String maxPermissibleSubscriptionPeriodUnit) {
        this.itemId = itemId;
        this.minPermissibleDiscount = minPermissibleDiscount;
        this.maxPermissibleDiscount = maxPermissibleDiscount;
        this.maxPermissibleUnits = this.maxPermissibleUnits;
        this.maxPermissibleSubscriptionPeriod = maxPermissibleSubscriptionPeriod;
        this.maxPermissibleSubscriptionPeriodUnit = maxPermissibleSubscriptionPeriodUnit;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public String getMaxPermissibleSubscriptionPeriodUnit() {
        return maxPermissibleSubscriptionPeriodUnit;
    }

    public void setMaxPermissibleSubscriptionPeriodUnit(String maxPermissibleSubscriptionPeriodUnit) {
        this.maxPermissibleSubscriptionPeriodUnit = maxPermissibleSubscriptionPeriodUnit;
    }
}
