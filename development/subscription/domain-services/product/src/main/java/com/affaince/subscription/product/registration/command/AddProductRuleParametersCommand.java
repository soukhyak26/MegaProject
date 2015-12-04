package com.affaince.subscription.product.registration.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class AddProductRuleParametersCommand {
    @TargetAggregateIdentifier
    private String itemId;
    private float minPermissibleDiscount;
    private float maxPermissibleDiscount;
    private int discountUnitCode;
    private short maxPermissibleUnits;
    private short maxPermissibleSubscriptionPeriod;
    private int maxPermissibleSubscriptionPeriodUnitCode;

    public AddProductRuleParametersCommand(String itemId, float minPermissibleDiscount, float maxPermissibleDiscount, int discountUnitCode, short maxPermissibleUnits, short maxPermissibleSubscriptionPeriod, int maxPermissibleSubscriptionPeriodUnitCode) {
        this.itemId = itemId;
        this.minPermissibleDiscount = minPermissibleDiscount;
        this.maxPermissibleDiscount = maxPermissibleDiscount;
        this.discountUnitCode = discountUnitCode;
        this.maxPermissibleUnits = maxPermissibleUnits;
        this.maxPermissibleSubscriptionPeriod = maxPermissibleSubscriptionPeriod;
        this.maxPermissibleSubscriptionPeriodUnitCode = maxPermissibleSubscriptionPeriodUnitCode;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public float getMinPermissibleDiscount() {
        return minPermissibleDiscount;
    }

    public void setMinPermissibleDiscount(float minPermissibleDiscount) {
        this.minPermissibleDiscount = minPermissibleDiscount;
    }

    public float getMaxPermissibleDiscount() {
        return maxPermissibleDiscount;
    }

    public void setMaxPermissibleDiscount(float maxPermissibleDiscount) {
        this.maxPermissibleDiscount = maxPermissibleDiscount;
    }

    public int getDiscountUnitCode() {
        return discountUnitCode;
    }

    public void setDiscountUnitCode(int discountUnitCode) {
        this.discountUnitCode = discountUnitCode;
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

    public int getMaxPermissibleSubscriptionPeriodUnitCode() {
        return maxPermissibleSubscriptionPeriodUnitCode;
    }

    public void setMaxPermissibleSubscriptionPeriodUnitCode(int maxPermissibleSubscriptionPeriodUnitCode) {
        this.maxPermissibleSubscriptionPeriodUnitCode = maxPermissibleSubscriptionPeriodUnitCode;
    }
}
