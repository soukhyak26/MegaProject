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

    public AddBasketRulesCommand(String basketRuleId, double maximumPermissibleAmount, double minimumAmountForDiscountEligibility, float maximumPermissibleDiscount, int maximumPermissibleDiscountUnit) {
        BasketRuleId = basketRuleId;
        this.maximumPermissibleAmount = maximumPermissibleAmount;
        this.minimumAmountForDiscountEligibility = minimumAmountForDiscountEligibility;
        this.maximumPermissibleDiscount = maximumPermissibleDiscount;
        this.maximumPermissibleDiscountUnit = maximumPermissibleDiscountUnit;
    }

    public AddBasketRulesCommand() {
    }

    public String getBasketRuleId() {
        return BasketRuleId;
    }

    public void setBasketRuleId(String basketRuleId) {
        BasketRuleId = basketRuleId;
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
