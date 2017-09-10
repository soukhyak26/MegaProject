package com.affaince.subscription.business.query.repository;

import com.affaince.subscription.business.query.view.ProductForecastView;
import com.affaince.subscription.common.type.ForecastContentStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 30-04-2016.
 */

public interface ProductForecastViewRepository extends CrudRepository<ProductForecastView, ProductVersionId> {
    List<ProductForecastView> findByProductVersionId_ProductId(String productId);

    List<ProductForecastView> findByProductVersionId_ProductId(String productId, Sort sort);

    List<ProductForecastView> findByProductVersionId(ProductVersionId productVersionId, Sort sort);

    ProductForecastView findFirstByProductVersionId_ProductIdOrderByProductVersionId_FromDateDesc(String productId);

    List<ProductForecastView> findByProductVersionId_ProductIdAndForecastContentStatusOrderByProductVersionId_FromDateDesc(String productId, ForecastContentStatus forecastContentStatus);

    List<ProductForecastView> findByProductVersionId_ProductIdAndProductVersionId_FromDateGreaterThan(String productId, LocalDate fromDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndProductVersionId_FromDateLessThan(String productId, LocalDate fromDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndProductVersionId_FromDateBetween(String productId, LocalDate startDate, LocalDate endDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateGreaterThan(String productId, LocalDate endDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateLessThan(String productId, LocalDate endDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateLessThan(String productId, LocalDate endDate, Sort sort);
    List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateBetween(String productId, LocalDate startDate, LocalDate endDate);
}
