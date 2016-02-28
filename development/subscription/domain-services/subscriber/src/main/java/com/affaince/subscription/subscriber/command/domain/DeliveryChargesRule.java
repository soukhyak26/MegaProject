package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.affaince.subscription.subscriber.command.event.DeliveryChargesRuleAddedEvent;
import com.affaince.subscription.subscriber.vo.RangeRule;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.List;

/**
 * Created by rsavaliya on 27/2/16.
 */
public class DeliveryChargesRule extends AbstractAnnotatedAggregateRoot <Integer>{
    @AggregateIdentifier
    private Integer ruleId;
    private List<RangeRule> deliveryChargesRules;

    public DeliveryChargesRule(Integer ruleId, List<RangeRule> deliveryChargesRules) {
        apply(new DeliveryChargesRuleAddedEvent(DeliveryChargesRuleType.valueOf(ruleId), deliveryChargesRules));
    }

    @EventSourcingHandler
    public void on (DeliveryChargesRuleAddedEvent event) {
        this.ruleId = event.getRuleId().getDeliveryChargesRuleTypeCode();
        this.deliveryChargesRules = event.getDeliveryChargesRules();
    }

    public List<RangeRule> getDeliveryChargesRules() {
        return deliveryChargesRules;
    }
}
