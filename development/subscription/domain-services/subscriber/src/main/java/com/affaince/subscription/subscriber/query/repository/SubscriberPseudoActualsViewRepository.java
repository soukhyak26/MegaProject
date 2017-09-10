package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.query.view.SubscriberPseudoActualsView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/10/2017.
 */
public interface SubscriberPseudoActualsViewRepository extends CrudRepository<SubscriberPseudoActualsView, LocalDate> {
    public List<SubscriberPseudoActualsView> findByForecastContentStatusAndForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
}
