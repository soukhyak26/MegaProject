package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.common.type.Discount;
import com.affaince.subscription.subscriber.vo.SubscriptionValueRange;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by rbsavaliya on 27-09-2015.
 */
@Document(collection = "SubscriptionRule")
public class SubscriptionRuleView {
    @Id
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


    public SubscriptionRuleView(String basketRuleId, double maximumPermissibleAmount, double minimumAmountForDiscountEligibility, Discount maximumPermissibleDiscount, int minimumAmountEligibleForFreeShipping, int diffBetweenDeliveryPreparationAndDispatchDate,int actualsAggregationPeriodForTargetForecast, double contingencyStockPercentage,List<SubscriptionValueRange> subscriptionValueRanges) {
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

    public List<SubscriptionValueRange> getSubscriptionValueRanges() {
        return subscriptionValueRanges;
    }

    public void setSubscriptionValueRanges(List<SubscriptionValueRange> subscriptionValueRanges) {
        this.subscriptionValueRanges = subscriptionValueRanges;
    }
}
