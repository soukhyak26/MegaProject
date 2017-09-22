package com.affaince.subscription.subscriber.web.request;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
public class SubscriptionRulesRequest {

    private double maximumPermissibleAmount;
    private double minimumAmountForDiscountEligibility;
    private float maximumPermissibleDiscount;
    private int maximumPermissibleDiscountUnit;
    private int minimumAmountEligibleForFreeShipping;
    private int diffBetweenDeliveryPreparationAndDispatchDate;
    private int actualsAggregationPeriodForTargetForecast=30;
    //stock in excess to predicted count to be kept as a contingency
    private double contingencyStockPercentage=0.1;


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

    public int getMinimumAmountEligibleForFreeShipping() {
        return minimumAmountEligibleForFreeShipping;
    }

    public void setMinimumAmountEligibleForFreeShipping(int minimumAmountEligibleForFreeShipping) {
        this.minimumAmountEligibleForFreeShipping = minimumAmountEligibleForFreeShipping;
    }

    public int getDiffBetweenDeliveryPreparationAndDispatchDate() {
        return diffBetweenDeliveryPreparationAndDispatchDate;
    }

    public void setDiffBetweenDeliveryPreparationAndDispatchDate(int diffBetweenDeliveryPreparationAndDispatchDate) {
        this.diffBetweenDeliveryPreparationAndDispatchDate = diffBetweenDeliveryPreparationAndDispatchDate;
    }

    public int getActualsAggregationPeriodForTargetForecast() {
        return actualsAggregationPeriodForTargetForecast;
    }

    public void setActualsAggregationPeriodForTargetForecast(int actualsAggregationPeriodForTargetForecast) {
        this.actualsAggregationPeriodForTargetForecast = actualsAggregationPeriodForTargetForecast;
    }

    public double getContingencyStockPercentage() {
        return contingencyStockPercentage;
    }

    public void setContingencyStockPercentage(double contingencyStockPercentage) {
        this.contingencyStockPercentage = contingencyStockPercentage;
    }
}
