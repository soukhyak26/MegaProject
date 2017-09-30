package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.subscriber.query.view.DeliveryForecastTrendView;
import com.affaince.subscription.subscriber.vo.DeliveryVersionId;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 9/30/2017.
 */
public interface DeliveryForecastTrendViewRepository extends CrudRepository<DeliveryForecastTrendView,DeliveryVersionId> {
}
