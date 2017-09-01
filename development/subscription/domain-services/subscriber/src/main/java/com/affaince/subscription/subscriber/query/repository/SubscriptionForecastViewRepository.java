package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.subscriber.query.view.SubscriptionForecastView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 8/30/2017.
 */
public interface SubscriptionForecastViewRepository extends CrudRepository<SubscriptionForecastView, LocalDate> {
}
