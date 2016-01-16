package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.subscriber.command.event.SubscriptionSpecificExpenseAddedEvent;
import com.affaince.subscription.subscriber.vo.RangeRule;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.List;

/**
 * Created by rbsavaliya on 16-01-2016.
 */

public class SubscriptionSpecificOperatingExpense extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String id;
    private String expenseHeader;
    private List<RangeRule> deliveryChargesRules;

    public SubscriptionSpecificOperatingExpense(String id, String expenseHeader, List<RangeRule> deliveryChargesRules) {
        apply(new SubscriptionSpecificExpenseAddedEvent(id, expenseHeader, deliveryChargesRules));
    }

    public SubscriptionSpecificOperatingExpense() {
    }

    public double findValueBetween(double min, double max) {
        RangeRule rule = new RangeRule();
        rule.setRuleMinimum(min);
        rule.setRuleMaximum(max);
        if (deliveryChargesRules.contains(rule)) {
            return deliveryChargesRules.get(deliveryChargesRules.indexOf(rule)).getApplicableValue();
        }
        return 0;
    }

    @EventSourcingHandler
    public void on(SubscriptionSpecificExpenseAddedEvent event) {
        this.id = event.getId();
        this.expenseHeader = event.getExpenseHeader();
        this.deliveryChargesRules = event.getDeliveryChargesRules();
    }
}
