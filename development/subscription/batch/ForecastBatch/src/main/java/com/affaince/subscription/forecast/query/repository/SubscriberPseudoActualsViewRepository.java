package com.affaince.subscription.forecast.query.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.forecast.query.view.SubscriberPseudoActualsView;
import com.affaince.subscription.forecast.vo.SubscriberVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/10/2017.
 */
public interface SubscriberPseudoActualsViewRepository extends CrudRepository<SubscriberPseudoActualsView, SubscriberVersionId> {
    public List<SubscriberPseudoActualsView> findByForecastContentStatusAndSubscriberVersionId_ForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscriberPseudoActualsView> findByForecastContentStatusAndSubscriberVersionId_ForecastDate(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscriberPseudoActualsView> findByForecastContentStatusAndSubscriberVersionId_RegistrationDateDesc(ForecastContentStatus forecastContentStatus);
}
