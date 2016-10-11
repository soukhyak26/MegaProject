package com.affaince.subscription.subscriber.services.deliverychargesrule;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.affaince.subscription.subscriber.command.domain.DeliveryChargesRule;
import com.affaince.subscription.subscriber.query.repository.DeliveryChargesRuleViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryChargesRuleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 11-10-2016.
 */
@Component
public class DeliveryChargesRulesService {

    private final DeliveryChargesRuleViewRepository repository;

    @Autowired
    public DeliveryChargesRulesService(DeliveryChargesRuleViewRepository repository) {
        this.repository = repository;
    }

    public DeliveryChargesRule findActiveDeliveryChargesRule() {
        DeliveryChargesRuleView deliveryChargesRuleView = repository.findFirstByRuleIdOrderByEffectiveDateDesc(
                DeliveryChargesRuleType.CHARGES_ON_DELIVERY_WEIGHT
        );
        DeliveryChargesRule deliveryChargesRule = new DeliveryChargesRule(DeliveryChargesRuleType.CHARGES_ON_DELIVERY_WEIGHT,
                deliveryChargesRuleView.getRangeRules(), deliveryChargesRuleView.getEffectiveDate());
        return deliveryChargesRule;
    }
}
