package com.verifier.domains.subscriber.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.DeliveryForecastVersionId;
import com.verifier.domains.subscriber.view.DeliveryPseudoActualsView;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/23/2017.
 */
public interface DeliveryPseudoActualsViewRepository extends CrudRepository<DeliveryPseudoActualsView,DeliveryForecastVersionId> {
    List<DeliveryPseudoActualsView> findAll();
    List<DeliveryPseudoActualsView> findByForecastContentStatusAndDeliveryForecastVersionId_ForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    List<DeliveryPseudoActualsView> findByForecastContentStatusAndDeliveryForecastVersionId_WeightRangeMinAndDeliveryForecastVersionId_WeightRangeMaxOrderByDeliveryForecastVersionId_DeliveryDateDesc(ForecastContentStatus forecastContentStatus, double minWieght, double maxWeight);
    List<DeliveryPseudoActualsView> findByForecastContentStatusAndDeliveryForecastVersionId_WeightRangeMinGreaterThanEqualAndDeliveryForecastVersionId_WeightRangeMaxLessThanOrderByDeliveryForecastVersionId_ForecastDateDesc(ForecastContentStatus forecastContentStatus, double weightRangeMin, double weightRangeMax);
}
