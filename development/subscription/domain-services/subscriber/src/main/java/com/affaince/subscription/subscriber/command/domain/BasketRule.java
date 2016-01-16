package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.Discount;
import com.affaince.subscription.subscriber.command.event.BasketRuleAddedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

/**
 * Created by rbsavaliya on 26-09-2015.
 */
public class BasketRule extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String basketRuleId;
    private double maximumPermissibleAmount;
    private double minimumAmountForDiscountEligibility;
    private Discount maximumPermissibleDiscount;
    private int minimumAmountEligibleForFreeShipping;

    public BasketRule() {
    }

    public BasketRule(String basketRuleId, double maximumPermissibleAmount, double minimumAmountForDiscountEligibility, Discount maximumPermissibleDiscount, int minimumAmountEligibleForFreeShipping) {
        apply(new BasketRuleAddedEvent(basketRuleId, maximumPermissibleAmount, minimumAmountForDiscountEligibility, maximumPermissibleDiscount, minimumAmountEligibleForFreeShipping));

    }

    public BasketRule(String basketRuleId, double maximumPermissibleAmount, double minimumAmountForDiscountEligibility, Discount maximumPermissibleDiscount) {
    }

    @EventSourcingHandler
    public void on(BasketRuleAddedEvent event) {
        this.basketRuleId = event.getBasketRuleId();
        this.maximumPermissibleAmount = event.getMaximumPermissibleAmount();
        this.minimumAmountForDiscountEligibility = event.getMinimumAmountForDiscountEligibility();
        this.maximumPermissibleDiscount = event.getMaximumPermissibleDiscount();
    }
}
