package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
public class AddBasketRulesCommand {
    @TargetAggregateIdentifier
    private String BasketRuleId;
    private double maximumPermissibleAmount;
    private double minimumAmountForDiscountEligibility;
    private float maximumPermissibleDiscount;
    private int maximumPermissibleDiscountUnit;
    private int minimumAmountEligibleForFreeShipping;

    public AddBasketRulesCommand(String basketRuleId, double maximumPermissibleAmount, double minimumAmountForDiscountEligibility, float maximumPermissibleDiscount, int maximumPermissibleDiscountUnit, int minimumAmountEligibleForFreeShipping) {
        BasketRuleId = basketRuleId;
        this.maximumPermissibleAmount = maximumPermissibleAmount;
        this.minimumAmountForDiscountEligibility = minimumAmountForDiscountEligibility;
        this.maximumPermissibleDiscount = maximumPermissibleDiscount;
        this.maximumPermissibleDiscountUnit = maximumPermissibleDiscountUnit;
        this.minimumAmountEligibleForFreeShipping = minimumAmountEligibleForFreeShipping;
    }

    public AddBasketRulesCommand() {
    }

    public String getBasketRuleId() {
        return BasketRuleId;
    }

    public double getMaximumPermissibleAmount() {
        return maximumPermissibleAmount;
    }

    public double getMinimumAmountForDiscountEligibility() {
        return minimumAmountForDiscountEligibility;
    }

    public float getMaximumPermissibleDiscount() {
        return maximumPermissibleDiscount;
    }

    public int getMaximumPermissibleDiscountUnit() {
        return maximumPermissibleDiscountUnit;
    }

    public int getMinimumAmountEligibleForFreeShipping() {
        return minimumAmountEligibleForFreeShipping;
    }
}
