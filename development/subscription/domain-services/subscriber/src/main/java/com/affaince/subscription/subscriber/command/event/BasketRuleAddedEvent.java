package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.common.type.Discount;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
public class BasketRuleAddedEvent {
    private String basketRuleId;
    private double maximumPermissibleAmount;
    private double minimumAmountForDiscountEligibility;
    private Discount maximumPermissibleDiscount;
    private int minimumAmountEligibleForFreeShipping;

    public BasketRuleAddedEvent(String basketRuleId, double maximumPermissibleAmount, double minimumAmountForDiscountEligibility, Discount maximumPermissibleDiscount, int minimumAmountEligibleForFreeShipping) {
        this.basketRuleId = basketRuleId;
        this.maximumPermissibleAmount = maximumPermissibleAmount;
        this.minimumAmountForDiscountEligibility = minimumAmountForDiscountEligibility;
        this.maximumPermissibleDiscount = maximumPermissibleDiscount;
        this.minimumAmountEligibleForFreeShipping = minimumAmountEligibleForFreeShipping;
    }

    public BasketRuleAddedEvent() {
    }

    public String getBasketRuleId() {
        return basketRuleId;
    }

    public double getMaximumPermissibleAmount() {
        return maximumPermissibleAmount;
    }

    public double getMinimumAmountForDiscountEligibility() {
        return minimumAmountForDiscountEligibility;
    }

    public Discount getMaximumPermissibleDiscount() {
        return maximumPermissibleDiscount;
    }

    public int getMinimumAmountEligibleForFreeShipping() {
        return minimumAmountEligibleForFreeShipping;
    }
}
