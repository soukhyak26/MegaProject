package com.verifier.domains.subscriber.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.verifier.domains.subscriber.view.SubscriberPseudoActualsView;
import com.verifier.domains.subscriber.vo.SubscriberVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/10/2017.
 */
public interface SubscriberPseudoActualsViewRepository extends CrudRepository<SubscriberPseudoActualsView, SubscriberVersionId> {
    public List<SubscriberPseudoActualsView> findAll();
    public List<SubscriberPseudoActualsView> findByForecastContentStatus(ForecastContentStatus forecastContentStatus);
    public List<SubscriberPseudoActualsView> findByForecastContentStatusAndSubscriberVersionId_ForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscriberPseudoActualsView> findByForecastContentStatusAndSubscriberVersionId_ForecastDate(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscriberPseudoActualsView> findByForecastContentStatusOrderBySubscriberVersionId_StartDateDesc(ForecastContentStatus forecastContentStatus);
}
