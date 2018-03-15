package com.verifier.domains.subscriber.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.verifier.domains.subscriber.view.SubscriptionPseudoActualsView;
import com.verifier.domains.subscriber.vo.SubscriptionVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/10/2017.
 */
public interface SubscriptionPseudoActualsViewRepository extends CrudRepository<SubscriptionPseudoActualsView, SubscriptionVersionId> {
    public List<SubscriptionPseudoActualsView> findByForecastContentStatus(ForecastContentStatus forecastContentStatus);
    public List<SubscriptionPseudoActualsView> findByForecastContentStatusAndSubscriptionVersionId_ForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscriptionPseudoActualsView> findByForecastContentStatusAndSubscriptionVersionId_ForecastDate(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscriptionPseudoActualsView> findByForecastContentStatusOrderBySubscriptionVersionId_StartDateDesc(ForecastContentStatus forecastContentStatus);
    public List<SubscriptionPseudoActualsView> findByForecastContentStatusAndSubscriptionVersionId_ValueRangeMinGreaterThanEqualAndSubscriptionVersionId_ValueRangeMaxLessThanOrderBySubscriptionVersionId_ForecastDateDesc(ForecastContentStatus forecastContentStatus, double minValue, double maxValue);
}
