package com.verifier.domains.business.repository;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.verifier.domains.business.view.DeliveryChargesRuleView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rsavaliya on 21/1/16.
 */
public interface BusinessDeliveryChargesRuleViewRepository extends CrudRepository<DeliveryChargesRuleView, DeliveryChargesRuleType> {
    List<DeliveryChargesRuleView> findAll();
    com.verifier.domains.subscriber.view.DeliveryChargesRuleView findByRuleId (DeliveryChargesRuleType ruleId);

    com.verifier.domains.subscriber.view.DeliveryChargesRuleView findFirstByRuleIdOrderByEffectiveDateDesc(DeliveryChargesRuleType ruleId);
}
