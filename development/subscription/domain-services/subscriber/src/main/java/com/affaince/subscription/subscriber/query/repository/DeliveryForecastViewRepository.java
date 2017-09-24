package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.subscriber.query.view.DeliveryForecastView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/23/2017.
 */
public interface DeliveryForecastViewRepository extends CrudRepository<DeliveryForecastView,LocalDate> {
    public List<DeliveryForecastView> findByForecastContentStatusAndForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<DeliveryForecastView> findByForecastContentStatusOrderByForecastDateDesc(ForecastContentStatus forecastContentStatus);
    public List<DeliveryForecastView> findByForecastContentStatusAndWeightRangeMinGreaterThanEqualAndWeightRangeMaxLessThanOrderByForecastDateDesc(ForecastContentStatus forecastContentStatus,double minWeight, double maxWeight);
}
