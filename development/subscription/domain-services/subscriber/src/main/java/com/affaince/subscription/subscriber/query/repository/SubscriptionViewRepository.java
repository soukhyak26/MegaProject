package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
public interface SubscriptionViewRepository extends CrudRepository<SubscriptionView, String> {
    SubscriptionView findBySubscriberId(String subscriberId);
}
