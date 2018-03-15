package com.verifier.domains.subscriber.repository;

import com.verifier.domains.subscriber.view.SubscribersActualsView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/1/2017.
 */
public interface SubscribersActualsViewRepository extends CrudRepository<SubscribersActualsView,LocalDate> {
    List<SubscribersActualsView> findAllByOrderByRegistrationDateDesc();
}
