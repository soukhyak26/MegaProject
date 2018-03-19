package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.SubscriptionForecastView;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.verifier.domains.business.vo.SubscriptionVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandar on 8/30/2017.
 */
public interface BusinessSubscriptionForecastViewRepository extends CrudRepository<SubscriptionForecastView, SubscriptionVersionId> {
    public List<SubscriptionForecastView> findByEndDateBetween(LocalDate endDate1, LocalDate endDate2);
    public List<SubscriptionForecastView> findByEndDateLessThan(LocalDate endDate1, Sort sort);
    public List<SubscriptionForecastView> findByForecastContentStatusOrderBySubscriptionVersionId_StartDateAsc(ForecastContentStatus forecastContentStatus);
    public List<SubscriptionForecastView> findByForecastContentStatusAndSubscriptionVersionId_ForecastDateLessThan(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscriptionForecastView> findByForecastContentStatusOrderBySubscriptionVersionId_ForecastDateDesc(ForecastContentStatus forecastContentStatus);
    public List<SubscriptionForecastView> findByForecastContentStatusAndSubscriptionVersionId_ForecastDate(ForecastContentStatus forecastContentStatus, LocalDate forecastDate);
    public List<SubscriptionForecastView> findByForecastContentStatusAndSubscriptionVersionId_ValueRangeMinGreaterThanEqualAndSubscriptionVersionId_ValueRangeMaxLessThanOrderBySubscriptionVersionId_ForecastDateDesc(ForecastContentStatus forecastContentStatus, double minValue, double maxValue);
    public List<SubscriptionForecastView> findByForecastContentStatusAndSubscriptionVersionId_StartDateAndEndDateAndSubscriptionVersionId_ValueRangeMinAndSubscriptionVersionId_ValueRangeMax(ForecastContentStatus forecastContentStatus, LocalDate startDate, LocalDate endDate, double valueRangeMin, double valueRangeMax);
}
