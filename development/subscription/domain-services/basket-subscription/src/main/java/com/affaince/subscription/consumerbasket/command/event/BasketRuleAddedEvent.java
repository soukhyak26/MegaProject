package com.affaince.subscription.consumerbasket.command.event;

import com.affaince.subscription.common.type.Discount;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
public class BasketRuleAddedEvent {
    private String basketRuleId;
    private double maximumPermissibleAmount;
    private double minimumAmountForDiscountEligibility;
    private Discount maximumPermissibleDiscount;

    public BasketRuleAddedEvent(String basketRuleId, double maximumPermissibleAmount, double minimumAmountForDiscountEligibility, Discount maximumPermissibleDiscount) {
        this.basketRuleId = basketRuleId;
        this.maximumPermissibleAmount = maximumPermissibleAmount;
        this.minimumAmountForDiscountEligibility = minimumAmountForDiscountEligibility;
        this.maximumPermissibleDiscount = maximumPermissibleDiscount;
    }

    public BasketRuleAddedEvent() {
    }

    public String getBasketRuleId() {
        return basketRuleId;
    }

    public void setBasketRuleId(String basketRuleId) {
        this.basketRuleId = basketRuleId;
    }

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

    public Discount getMaximumPermissibleDiscount() {
        return maximumPermissibleDiscount;
    }

    public void setMaximumPermissibleDiscount(Discount maximumPermissibleDiscount) {
        this.maximumPermissibleDiscount = maximumPermissibleDiscount;
    }
}
