package com.affaince.subscription.business.query.repository;

import com.affaince.subscription.business.query.view.ProductForecastView;
import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import org.joda.time.LocalDateTime;
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

    List<ProductForecastView> findByProductVersionId_ProductIdAndProductVersionId_FromDateGreaterThan(String productId, LocalDateTime fromDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndProductVersionId_FromDateLessThan(String productId, LocalDateTime fromDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndProductVersionId_FromDateBetween(String productId, LocalDateTime startDate, LocalDateTime endDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateGreaterThan(String productId, LocalDateTime endDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateLessThan(String productId, LocalDateTime endDate);

    List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateBetween(String productId, LocalDateTime startDate, LocalDateTime endDate);
}