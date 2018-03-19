package com.verifier.domains.business.repository;

import com.verifier.domains.business.view.ProductForecastView;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ForecastVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 30-04-2016.
 */

public interface BusinessProductForecastViewRepository extends CrudRepository<ProductForecastView, ForecastVersionId> {
    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastVersionId_StartDateGreaterThanEqual(String productId, LocalDate fromDate);
    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastVersionId_StartDateBetween(String productId, LocalDate startDate, LocalDate endDate);
    List<ProductForecastView> findByForecastVersionId_ProductIdAndEndDateBetween(String productId, LocalDate startDate, LocalDate endDate);
    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastVersionId_StartDateAndEndDateAndForecastContentStatus(String productId, LocalDate startDate, LocalDate endDate, ForecastContentStatus forecastContentStatus);
}
