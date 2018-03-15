package com.verifier.domains.subscriber.repository;

import com.verifier.domains.subscriber.view.SubscriptionSummaryView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rsavaliya on 9/1/16.
 */
public interface SubscriptionSummaryViewRepository extends CrudRepository<SubscriptionSummaryView, Integer> {
}
