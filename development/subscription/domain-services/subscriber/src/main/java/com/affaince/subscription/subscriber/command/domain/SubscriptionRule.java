package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.Discount;
import com.affaince.subscription.subscriber.command.event.SubscriptionRuleAddedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
public class SubscriptionRule extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String basketRuleId;
    private double maximumPermissibleAmount;
    private double minimumAmountForDiscountEligibility;
    private Discount maximumPermissibleDiscount;
    private int minimumAmountEligibleForFreeShipping;
    private int diffBetweenDeliveryPreparationAndDispatchDate;
    private int actualsAggregationPeriodForTargetForecast=30;
    //stock in excess to predicted count to be kept as a contingency
    private double contingencyStockPercentage=0.1;


    public SubscriptionRule() {
    }

    public SubscriptionRule(String basketRuleId,
                            double maximumPermissibleAmount,
                            double minimumAmountForDiscountEligibility,
                            Discount maximumPermissibleDiscount,
                            int minimumAmountEligibleForFreeShipping,
                            int diffBetweenDeliveryPreparationAndDispatchDate,
                            int actualsAggregationPeriodForTargetForecast,
                            double contingencyStockPercentage) {
        apply(new SubscriptionRuleAddedEvent(basketRuleId, maximumPermissibleAmount,
                minimumAmountForDiscountEligibility, maximumPermissibleDiscount,
                minimumAmountEligibleForFreeShipping, diffBetweenDeliveryPreparationAndDispatchDate,actualsAggregationPeriodForTargetForecast,contingencyStockPercentage));
    }

    @EventSourcingHandler
    public void on(SubscriptionRuleAddedEvent event) {
        this.basketRuleId = event.getBasketRuleId();
        this.maximumPermissibleAmount = event.getMaximumPermissibleAmount();
        this.minimumAmountForDiscountEligibility = event.getMinimumAmountForDiscountEligibility();
        this.maximumPermissibleDiscount = event.getMaximumPermissibleDiscount();
        this.diffBetweenDeliveryPreparationAndDispatchDate = event.getDiffBetweenDeliveryPreparationAndDispatchDate();
        this.actualsAggregationPeriodForTargetForecast=event.getActualsAggregationPeriodForTargetForecast();
        this.contingencyStockPercentage=event.getContingencyStockPercentage();
    }
}
