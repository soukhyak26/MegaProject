package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.subscriber.query.view.DeliveryForecastView;
import com.affaince.subscription.subscriber.vo.DeliveryForecastVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/23/2017.
 */
public interface DeliveryForecastViewRepository extends CrudRepository<DeliveryForecastView,DeliveryForecastVersionId> {
    public List<DeliveryForecastView> findByEndDateBetween(LocalDate endDate1, LocalDate endDate2);
    public List<DeliveryForecastView> findByEndDateLessThan(LocalDate endDate1, Sort sort);
    public List<DeliveryForecastView> findByForecastContentStatusOrderBydeliveryVersionId_StartDateAsc(ForecastContentStatus forecastContentStatus);
    public List<DeliveryForecastView> findByForecastContentStatusOrderBySubscriptionVersionId_StartDateAsc(ForecastContentStatus forecastContentStatus );

    public List<DeliveryForecastView> findByForecastContentStatusAndDeliveryVersionId_ForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<DeliveryForecastView> findByForecastContentStatusOrderByDeliveryVersionId_ForecastDateDesc(ForecastContentStatus forecastContentStatus);
    public List<DeliveryForecastView> findByForecastContentStatusAndDeliveryVersionId_WeightRangeMinGreaterThanEqualAndDeliveryVersionId_WeightRangeMaxLessThanOrderByDeliveryVersionId_ForecastDateDesc(ForecastContentStatus forecastContentStatus,double minWeight, double maxWeight);
}
