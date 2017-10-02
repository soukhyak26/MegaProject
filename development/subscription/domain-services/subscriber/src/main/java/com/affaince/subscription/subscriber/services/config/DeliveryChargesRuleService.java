package com.affaince.subscription.subscriber.services.config;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.affaince.subscription.subscriber.query.repository.DeliveryChargesRuleViewRepository;
import com.affaince.subscription.subscriber.query.view.DeliveryChargesRuleView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 10/2/2017.
 */
@Component
public class DeliveryChargesRuleService {
    private final DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository;

    @Autowired
    public DeliveryChargesRuleService(DeliveryChargesRuleViewRepository deliveryChargesRuleViewRepository) {
        this.deliveryChargesRuleViewRepository = deliveryChargesRuleViewRepository;
    }

    public DeliveryChargesRuleView  getDeliveryConfig(){
        return deliveryChargesRuleViewRepository.findFirstByRuleIdOrderByEffectiveDateDesc(DeliveryChargesRuleType.CHARGES_ON_DELIVERY_WEIGHT);
    }
}
