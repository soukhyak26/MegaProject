package com.affaince.subscription.integration.query.repository;

import com.affaince.subscription.integration.query.view.SubscriptionableItemView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public interface SubscriptionableItemRepository extends CrudRepository <SubscriptionableItemView, String>{
    SubscriptionableItemView findOneByItemId(String itemId);
}
