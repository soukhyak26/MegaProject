package com.affiance.prediction.query.repository;

import com.affiance.prediction.query.view.ProductForecastView;
import com.affiance.prediction.vo.ProductForecastStatus;
import com.affiance.prediction.vo.ProductVersionId;
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

    List<ProductForecastView> findByProductVersionId_ProductIdAndProductForecastStatusOrderByProductVersionId_FromDateDesc(String productId, ProductForecastStatus productForecastStatus);

    List<ProductForecastView> findByProductVersionId_ProductIdAndProductVersionId_FromDateGreaterThan(String productId, LocalDate fromDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndProductVersionId_FromDateLessThan(String productId, LocalDate fromDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndProductVersionId_FromDateBetween(String productId, LocalDate startDate, LocalDate endDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateGreaterThan(String productId, LocalDate endDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateLessThan(String productId, LocalDate endDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateLessThan(String productId, LocalDate endDate, Sort sort);
    List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateBetween(String productId, LocalDate startDate, LocalDate endDate);
}