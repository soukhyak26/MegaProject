package com.affaince.subscription.product.registration.web.request;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class AddRuleParametersRequest {

    private float minPermissibleDiscount;
    private float maxPermissibleDiscount;
    private int discountUnitCode;
    private short maxPermissibleUnits;
    private short maxPermissibleSubscriptionPeriod;
    private int maxPermissibleSubscriptionPeriodUnitCode;

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
