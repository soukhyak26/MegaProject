package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.subscriber.query.view.SubscriberForecastTrendView;
import com.affaince.subscription.subscriber.vo.SubscriberTrendVersionId;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 9/30/2017.
 */
public interface SubscriberForecastTrendViewRepository extends CrudRepository<SubscriberForecastTrendView,SubscriberTrendVersionId> {
}
