package com.affaince.subscription.forecast.query.repository;

import com.affaince.subscription.forecast.query.view.SubscribersActualsView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/1/2017.
 */
public interface SubscribersActualsViewRepository extends CrudRepository<SubscribersActualsView,LocalDate> {
    List<SubscribersActualsView> findByRegistrationDateDesc();
}
