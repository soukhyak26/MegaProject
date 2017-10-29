package com.affaince.subscription.forecast.query.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.forecast.query.view.SubscriptionPseudoActualsView;
import com.affaince.subscription.forecast.vo.SubscriptionVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/10/2017.
 */
public interface SubscriptionPseudoActualsViewRepository extends CrudRepository<SubscriptionPseudoActualsView, SubscriptionVersionId> {
    public List<SubscriptionPseudoActualsView> findByForecastContentStatusAndSubscriptionVersionId_ForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscriptionPseudoActualsView> findByForecastContentStatusAndSubscriptionVersionId_ForecastDate(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscriptionPseudoActualsView> findByForecastContentStatusAndSubscriptionVersionId_StartDateDesc(ForecastContentStatus forecastContentStatus);
}
