package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.affaince.subscription.subscriber.query.view.DeliveryChargesRuleView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rsavaliya on 21/1/16.
 */
public interface DeliveryChargesRuleViewRepository extends CrudRepository<DeliveryChargesRuleView, DeliveryChargesRuleType> {

}
