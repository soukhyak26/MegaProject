package com.affaince.subscription.business.distribution.db;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.DeliveryForecastVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeliveryForecastViewRepository extends CrudRepository<DeliveryForecastView, DeliveryForecastVersionId> {
    List<DeliveryForecastView> findByEndDateBetween(LocalDate endDate1, LocalDate endDate2);

    List<DeliveryForecastView> findByEndDateLessThan(LocalDate endDate1, Sort sort);

    // public List<DeliveryForecastView> findByForecastContentStatusOrderByDeliveryForecastVersionId_ForecastDateAsc(ForecastContentStatus forecastContentStatus);
    List<DeliveryForecastView> findByForecastContentStatusOrderByDeliveryForecastVersionId_DeliveryDateAsc(ForecastContentStatus forecastContentStatus);

    List<DeliveryForecastView> findByForecastContentStatusAndDeliveryForecastVersionId_ForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);

    // public List<DeliveryForecastView> findByForecastContentStatusOrderByDeliveryForecastVersionId_ForecastDateDesc(ForecastContentStatus forecastContentStatus);
    //public List<DeliveryForecastView> findByForecastContentStatusAndDeliveryForecastVersionId_WeightRangeMinGreaterThanEqualAndDeliveryForecastVersionId_WeightRangeMaxLessThanOrderByDeliveryForecastVersionId_ForecastDateDesc(ForecastContentStatus forecastContentStatus, double minWeight, double maxWeight);
    List<DeliveryForecastView> findByForecastContentStatusAndDeliveryForecastVersionId_WeightRangeMinAndDeliveryForecastVersionId_WeightRangeMaxOrderByDeliveryForecastVersionId_DeliveryDateAsc(ForecastContentStatus forecastContentStatus, double minWeight, double maxWeight);
}