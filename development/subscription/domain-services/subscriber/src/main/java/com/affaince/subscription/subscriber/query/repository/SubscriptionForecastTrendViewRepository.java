package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.subscriber.query.view.SubscriptionForecastTrendView;
import com.affaince.subscription.subscriber.vo.SubscriptionVersionId;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 9/30/2017.
 */
public interface SubscriptionForecastTrendViewRepository extends CrudRepository<SubscriptionForecastTrendView, SubscriptionVersionId> {

}
