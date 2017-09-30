package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.common.type.Discount;
import com.affaince.subscription.subscriber.vo.SubscriptionValueRange;

import java.util.List;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
public class SubscriptionRuleAddedEvent {
    private String basketRuleId;
    private double maximumPermissibleAmount;
    private double minimumAmountForDiscountEligibility;
    private Discount maximumPermissibleDiscount;
    private int minimumAmountEligibleForFreeShipping;
    private int diffBetweenDeliveryPreparationAndDispatchDate;
    private int actualsAggregationPeriodForTargetForecast=30;
    //stock in excess to predicted count to be kept as a contingency
    private double contingencyStockPercentage=0.1;
    private List<SubscriptionValueRange> subscriptionValueRanges;


    public SubscriptionRuleAddedEvent(String basketRuleId, double maximumPermissibleAmount, double minimumAmountForDiscountEligibility, Discount maximumPermissibleDiscount, int minimumAmountEligibleForFreeShipping, int diffBetweenDeliveryPreparationAndDispatchDate,int actualsAggregationPeriodForTargetForecast, double contingencyStockPercentage,List<SubscriptionValueRange> subscriptionValueRanges) {
        this.basketRuleId = basketRuleId;
        this.maximumPermissibleAmount = maximumPermissibleAmount;
        this.minimumAmountForDiscountEligibility = minimumAmountForDiscountEligibility;
        this.maximumPermissibleDiscount = maximumPermissibleDiscount;
        this.minimumAmountEligibleForFreeShipping = minimumAmountEligibleForFreeShipping;
        this.diffBetweenDeliveryPreparationAndDispatchDate = diffBetweenDeliveryPreparationAndDispatchDate;
        this.actualsAggregationPeriodForTargetForecast=actualsAggregationPeriodForTargetForecast;
        this.contingencyStockPercentage=contingencyStockPercentage;
        this.subscriptionValueRanges=subscriptionValueRanges;
    }

    public SubscriptionRuleAddedEvent() {
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

    public int getDiffBetweenDeliveryPreparationAndDispatchDate() {
        return diffBetweenDeliveryPreparationAndDispatchDate;
    }

    public int getActualsAggregationPeriodForTargetForecast() {
        return actualsAggregationPeriodForTargetForecast;
    }

    public double getContingencyStockPercentage() {
        return contingencyStockPercentage;
    }

    public List<SubscriptionValueRange> getSubscriptionValueRanges() {
        return subscriptionValueRanges;
    }
}
