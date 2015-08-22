package com.affaince.subscription.inventory.query.repository;

import com.affaince.subscription.inventory.query.view.BasketView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandark on 22-08-2015.
 */
public interface SubscriptionBasketRepository extends CrudRepository<BasketView, String> {
    BasketView findOneByBasketId (String basketId);
}
