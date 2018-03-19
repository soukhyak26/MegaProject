package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.DeliveryChargesRuleView;
import com.affaince.subscription.common.type.QuantityUnit;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by rsavaliya on 21/1/16.
 */
public interface BusinessDeliveryChargesRuleViewRepository extends CrudRepository<DeliveryChargesRuleView, String> {
    public List<DeliveryChargesRuleView> findByRuleMinimumAndRuleMaximumAndRuleUnit(double ruleMinimum, double ruleMaximum, QuantityUnit ruleUnit);
}
