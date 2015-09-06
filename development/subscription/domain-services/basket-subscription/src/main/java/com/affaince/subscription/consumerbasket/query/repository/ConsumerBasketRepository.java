package com.affaince.subscription.consumerbasket.query.repository;

import com.affaince.subscription.consumerbasket.query.view.ConsumerBasketView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public interface ConsumerBasketRepository extends CrudRepository<ConsumerBasketView, String> {
}
