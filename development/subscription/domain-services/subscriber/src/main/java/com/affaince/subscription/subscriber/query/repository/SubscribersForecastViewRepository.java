package com.affaince.subscription.subscriber.query.repository;

import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 9/1/2017.
 */
public interface SubscribersForecastViewRepository extends CrudRepository<SubscribersForecastViewRepository,LocalDate> {
}
