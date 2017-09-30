package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.subscriber.query.view.DeliveryPseudoActualsView;
import com.affaince.subscription.subscriber.vo.DeliveryVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/23/2017.
 */
public interface DeliveryPseudoActualsViewRepository extends CrudRepository<DeliveryPseudoActualsView,DeliveryVersionId> {
    List<DeliveryPseudoActualsView> findByForecastContentStatusAndDeliveryVersionId_ForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
}
