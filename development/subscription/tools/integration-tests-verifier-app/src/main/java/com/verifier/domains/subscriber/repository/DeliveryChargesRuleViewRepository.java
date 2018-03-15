package com.verifier.domains.subscriber.repository;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.verifier.domains.subscriber.view.DeliveryChargesRuleView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rsavaliya on 21/1/16.
 */
public interface DeliveryChargesRuleViewRepository extends CrudRepository<DeliveryChargesRuleView, DeliveryChargesRuleType> {
    DeliveryChargesRuleView findByRuleId (DeliveryChargesRuleType ruleId);

    DeliveryChargesRuleView findFirstByRuleIdOrderByEffectiveDateDesc(DeliveryChargesRuleType ruleId);
}
