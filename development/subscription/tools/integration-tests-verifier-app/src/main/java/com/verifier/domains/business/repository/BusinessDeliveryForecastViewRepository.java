package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.DeliveryForecastView;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.DeliveryForecastVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/23/2017.
 */
public interface BusinessDeliveryForecastViewRepository extends CrudRepository<DeliveryForecastView,DeliveryForecastVersionId> {
    public List<DeliveryForecastView> findByForecastContentStatus(ForecastContentStatus forecastContentStatus);
    public List<DeliveryForecastView> findByDeliveryForecastVersionId_WeightRangeMinAndDeliveryForecastVersionId_WeightRangeMaxAndForecastContentStatusOrderByDeliveryForecastVersionId_DeliveryDateAsc(double weightRangeMin, double weightRangeMax,ForecastContentStatus forecastContentStatus);
}
