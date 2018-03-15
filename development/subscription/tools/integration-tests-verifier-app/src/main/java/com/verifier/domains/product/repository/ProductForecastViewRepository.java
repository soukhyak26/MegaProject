package com.verifier.domains.product.repository;

import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ForecastVersionId;
import com.verifier.domains.product.view.ProductForecastView;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 30-04-2016.
 */

public interface ProductForecastViewRepository extends CrudRepository<ProductForecastView, ForecastVersionId> {
    List<ProductForecastView> findByForecastVersionId_ProductId(String productId);

    List<ProductForecastView> findByForecastVersionId_ProductId(String productId, Sort sort);

    ProductForecastView findFirstByForecastVersionId_ProductIdOrderByForecastVersionId_StartDateDesc(String productId);

    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastContentStatusOrderByForecastVersionId_StartDateDesc(String productId, ForecastContentStatus forecastContentStatus);

    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastContentStatusOrderByForecastVersionId_StartDateAsc(String productId, ForecastContentStatus forecastContentStatus);

    List<ProductForecastView> findByForecastVersionId_ProductIdAndEndDateLessThan(String productId, LocalDate endDate, Sort sort);

    List<ProductForecastView> findByForecastVersionId_ProductIdAndEndDateBetween(String productId, LocalDate startDate, LocalDate endDate);
    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastContentStatusAndForecastVersionId_StartDateAndEndDate(String productId, LocalDate startDate, LocalDate endDate);
    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastContentStatus(String productId, ForecastContentStatus forecastContentStatus);
    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastContentStatusOrderByForecastVersionId_ForecastDateDesc(String productId, ForecastContentStatus forecastContentStatus);
    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastContentStatusAndForecastVersionId_ForecastDateLessThan(String productId, ForecastContentStatus forecastContentStatus, LocalDate newForecastDate);
    List<ProductForecastView> findByForecastVersionId_ProductIdAndForecastContentStatusAndForecastVersionId_ForecastDate(String productId, ForecastContentStatus forecastContentStatus, LocalDate newForecastDate);
    void deleteAll();
}
