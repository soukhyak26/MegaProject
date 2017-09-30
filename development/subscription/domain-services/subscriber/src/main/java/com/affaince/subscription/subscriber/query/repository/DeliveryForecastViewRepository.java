package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.subscriber.query.view.DeliveryForecastView;
import com.affaince.subscription.subscriber.vo.DeliveryVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/23/2017.
 */
public interface DeliveryForecastViewRepository extends CrudRepository<DeliveryForecastView,DeliveryVersionId> {
    public List<DeliveryForecastView> findByForecastContentStatusAndDeliveryVersionId_ForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<DeliveryForecastView> findByForecastContentStatusOrderByDeliveryVersionId_ForecastDateDesc(ForecastContentStatus forecastContentStatus);
    public List<DeliveryForecastView> findByForecastContentStatusAndDeliveryVersionId_WeightRangeMinGreaterThanEqualAndDeliveryVersionId_WeightRangeMaxLessThanOrderByDeliveryVersionId_ForecastDateDesc(ForecastContentStatus forecastContentStatus,double minWeight, double maxWeight);
}
