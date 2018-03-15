package com.verifier.domains.subscriber.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.DeliveryForecastVersionId;
import com.verifier.domains.subscriber.view.DeliveryForecastView;
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
    public List<DeliveryForecastView> findByForecastContentStatusOrderByDeliveryForecastVersionId_ForecastDateAsc(ForecastContentStatus forecastContentStatus);

    public List<DeliveryForecastView> findByForecastContentStatusAndDeliveryForecastVersionId_ForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<DeliveryForecastView> findByForecastContentStatusOrderByDeliveryForecastVersionId_ForecastDateDesc(ForecastContentStatus forecastContentStatus);
    public List<DeliveryForecastView> findByForecastContentStatusAndDeliveryForecastVersionId_WeightRangeMinGreaterThanEqualAndDeliveryForecastVersionId_WeightRangeMaxLessThanOrderByDeliveryForecastVersionId_ForecastDateDesc(ForecastContentStatus forecastContentStatus, double minWeight, double maxWeight);
}
