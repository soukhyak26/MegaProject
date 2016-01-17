package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.subscriber.query.view.SubscriptionSummaryView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rsavaliya on 9/1/16.
 */
public interface SubscriptionSummaryViewRepository extends CrudRepository <SubscriptionSummaryView, Integer> {
}
