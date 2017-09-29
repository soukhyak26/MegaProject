package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.subscriber.query.view.SubscriptionPseudoActualsView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/10/2017.
 */
public interface SubscriptionPseudoActualsViewRepository extends CrudRepository<SubscriptionPseudoActualsView, LocalDate> {
    public List<SubscriptionPseudoActualsView> findByForecastContentStatusAndForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscriptionPseudoActualsView> findByForecastContentStatusAndForecastDate(ForecastContentStatus forecastContentStatus,LocalDate forecastDate);
}
