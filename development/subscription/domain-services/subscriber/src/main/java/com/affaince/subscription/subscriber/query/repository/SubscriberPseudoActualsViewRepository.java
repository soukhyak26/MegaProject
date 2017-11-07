package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.query.view.SubscriberPseudoActualsView;
import com.affaince.subscription.subscriber.vo.SubscriberVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/10/2017.
 */
public interface SubscriberPseudoActualsViewRepository extends CrudRepository<SubscriberPseudoActualsView, SubscriberVersionId> {
    public List<SubscriberPseudoActualsView> findByForecastContentStatusAndSubscriberVersionId_ForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscriberPseudoActualsView> findByForecastContentStatusAndSubscriberVersionId_ForecastDate(ForecastContentStatus forecastContentStatus,LocalDate forecastDate);
    public List<SubscriberPseudoActualsView> findByForecastContentStatusAndSubscriberVersionId_RegistrationDateDesc(ForecastContentStatus forecastContentStatus);
}
