package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.subscriber.query.view.SubscribersActualsView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 9/1/2017.
 */
public interface SubscribersActualsViewRepository extends CrudRepository<SubscribersActualsView,LocalDate> {
}
