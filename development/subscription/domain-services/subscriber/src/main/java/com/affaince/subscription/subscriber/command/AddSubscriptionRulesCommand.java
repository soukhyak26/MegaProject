package com.affaince.subscription.subscriber.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
public class AddSubscriptionRulesCommand {
    @TargetAggregateIdentifier
    private String BasketRuleId;
    private double maximumPermissibleAmount;
    private double minimumAmountForDiscountEligibility;
    private float maximumPermissibleDiscount;
    private int maximumPermissibleDiscountUnit;
    private int minimumAmountEligibleForFreeShipping;
    private int diffBetweenDeliveryPreparationAndDispatchDate;

    public AddSubscriptionRulesCommand(String basketRuleId, double maximumPermissibleAmount, double minimumAmountForDiscountEligibility, float maximumPermissibleDiscount, int maximumPermissibleDiscountUnit, int minimumAmountEligibleForFreeShipping, int diffBetweenDeliveryPreparationAndDispatchDate) {
        BasketRuleId = basketRuleId;
        this.maximumPermissibleAmount = maximumPermissibleAmount;
        this.minimumAmountForDiscountEligibility = minimumAmountForDiscountEligibility;
        this.maximumPermissibleDiscount = maximumPermissibleDiscount;
        this.maximumPermissibleDiscountUnit = maximumPermissibleDiscountUnit;
        this.minimumAmountEligibleForFreeShipping = minimumAmountEligibleForFreeShipping;
        this.diffBetweenDeliveryPreparationAndDispatchDate = diffBetweenDeliveryPreparationAndDispatchDate;
    }

    public AddSubscriptionRulesCommand() {
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

    public int getDiffBetweenDeliveryPreparationAndDispatchDate() {
        return diffBetweenDeliveryPreparationAndDispatchDate;
    }
}
