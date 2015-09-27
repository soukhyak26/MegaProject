package com.affaince.subscription.consumerbasket.query.repository;

import com.affaince.subscription.consumerbasket.query.view.BasketRuleView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rbsavaliya on 27-09-2015.
 */
public interface BasketRuleRepository extends CrudRepository<BasketRuleView, String> {
}
