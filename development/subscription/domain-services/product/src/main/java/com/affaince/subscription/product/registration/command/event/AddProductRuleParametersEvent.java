package com.affaince.subscription.product.registration.command.event;

import com.affaince.subscription.common.type.Discount;
import com.affaince.subscription.common.type.Period;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class AddProductRuleParametersEvent {
    private String itemId;
    private Discount minPermissibleDiscount;
    private Discount maxPermissibleDiscount;
    private short maxPermissibleUnits;
    private Period maxPermissibleSubscriptionPeriod;

    public AddProductRuleParametersEvent(String itemId, Discount minPermissibleDiscount, Discount maxPermissibleDiscount, short maxPermissibleUnits, Period maxPermissibleSubscriptionPeriod) {
        this.itemId = itemId;
        this.minPermissibleDiscount = minPermissibleDiscount;
        this.maxPermissibleDiscount = maxPermissibleDiscount;
        this.maxPermissibleUnits = maxPermissibleUnits;
        this.maxPermissibleSubscriptionPeriod = maxPermissibleSubscriptionPeriod;
    }

    public AddProductRuleParametersEvent() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public short getMaxPermissibleUnits() {
        return maxPermissibleUnits;
    }

    public void setMaxPermissibleUnits(short maxPermissibleUnits) {
        this.maxPermissibleUnits = maxPermissibleUnits;
    }

    public Period getMaxPermissibleSubscriptionPeriod() {
        return maxPermissibleSubscriptionPeriod;
    }

    public void setMaxPermissibleSubscriptionPeriod(Period maxPermissibleSubscriptionPeriod) {
        this.maxPermissibleSubscriptionPeriod = maxPermissibleSubscriptionPeriod;
    }
}
