package com.affaince.subscription.consumerbasket.query.repository;

import com.affaince.subscription.consumerbasket.query.view.BasketView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rbsavaliya on 02-10-2015.
 */
public interface BasketRepository extends CrudRepository<BasketView, String> {
}
