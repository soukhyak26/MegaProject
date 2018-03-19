package com.verifier.domains.subscriber.repository;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.verifier.domains.subscriber.view.DeliveryChargesRuleView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rsavaliya on 21/1/16.
 */
public interface DeliveryChargesRuleViewRepository extends CrudRepository<DeliveryChargesRuleView, DeliveryChargesRuleType> {
    List<DeliveryChargesRuleView> findAll();
    DeliveryChargesRuleView findByRuleId (DeliveryChargesRuleType ruleId);

    DeliveryChargesRuleView findFirstByRuleIdOrderByEffectiveDateDesc(DeliveryChargesRuleType ruleId);
}
