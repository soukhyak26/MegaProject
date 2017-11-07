package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.subscriber.query.view.SubscriptionActualsView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 8/30/2017.
 */
public interface SubscriptionActualsViewRepoitory extends CrudRepository<SubscriptionActualsView, LocalDate> {
    public List<SubscriptionActualsView> findByRegistrationDateDesc();
}
