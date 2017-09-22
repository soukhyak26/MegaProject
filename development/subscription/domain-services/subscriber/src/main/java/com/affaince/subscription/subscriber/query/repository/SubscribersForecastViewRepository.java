package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.subscriber.query.view.SubscribersForecastView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/1/2017.
 */
public interface SubscribersForecastViewRepository extends CrudRepository<SubscribersForecastView,LocalDate> {
    public List<SubscribersForecastView> findByForecastContentStatusAndForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscribersForecastView> findByForecastContentStatusOrderByForecastDateDesc(ForecastContentStatus forecastContentStatus);
}
