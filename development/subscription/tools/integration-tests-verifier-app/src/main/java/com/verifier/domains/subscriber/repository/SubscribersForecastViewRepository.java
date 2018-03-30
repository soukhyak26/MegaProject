package com.verifier.domains.subscriber.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.verifier.domains.subscriber.view.SubscribersForecastView;
import com.verifier.domains.subscriber.vo.SubscriberVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 9/1/2017.
 */
public interface SubscribersForecastViewRepository extends CrudRepository<SubscribersForecastView,SubscriberVersionId> {
    public List<SubscribersForecastView> findAll();
    public List<SubscribersForecastView> findByEndDateBetween(LocalDate endDate1, LocalDate endDate2);
    public List<SubscribersForecastView> findByEndDateLessThan(LocalDate endDate1, Sort sort);
    public List<SubscribersForecastView> findByForecastContentStatus(ForecastContentStatus forecastContentStatus);
    public List<SubscribersForecastView> findByForecastContentStatusAndEndDateLessThanEqual(ForecastContentStatus forecastContentStatus, LocalDate date);
    public List<SubscribersForecastView> findByForecastContentStatusOrderBySubscriberVersionId_StartDateAsc(ForecastContentStatus forecastContentStatus);
    public List<SubscribersForecastView> findByForecastContentStatusAndSubscriberVersionId_ForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscribersForecastView> findByForecastContentStatusOrderBySubscriberVersionId_ForecastDateDesc(ForecastContentStatus forecastContentStatus);
    public List<SubscribersForecastView> findByForecastContentStatusAndSubscriberVersionId_ForecastDate(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
}