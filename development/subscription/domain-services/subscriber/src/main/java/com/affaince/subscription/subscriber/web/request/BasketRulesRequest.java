package com.affaince.subscription.subscriber.web.request;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
public class BasketRulesRequest {

    private double maximumPermissibleAmount;
    private double minimumAmountForDiscountEligibility;
    private float maximumPermissibleDiscount;
    private int maximumPermissibleDiscountUnit;

    public double getMaximumPermissibleAmount() {
        return maximumPermissibleAmount;
    }

    public void setMaximumPermissibleAmount(double maximumPermissibleAmount) {
        this.maximumPermissibleAmount = maximumPermissibleAmount;
    }

    public double getMinimumAmountForDiscountEligibility() {
        return minimumAmountForDiscountEligibility;
    }

    public void setMinimumAmountForDiscountEligibility(double minimumAmountForDiscountEligibility) {
        this.minimumAmountForDiscountEligibility = minimumAmountForDiscountEligibility;
    }

    public float getMaximumPermissibleDiscount() {
        return maximumPermissibleDiscount;
    }

    public void setMaximumPermissibleDiscount(float maximumPermissibleDiscount) {
        this.maximumPermissibleDiscount = maximumPermissibleDiscount;
    }

    public int getMaximumPermissibleDiscountUnit() {
        return maximumPermissibleDiscountUnit;
    }

    public void setMaximumPermissibleDiscountUnit(int maximumPermissibleDiscountUnit) {
        this.maximumPermissibleDiscountUnit = maximumPermissibleDiscountUnit;
    }
}