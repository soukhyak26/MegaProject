package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductForecastView;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 30-04-2016.
 */

public interface ProductForecastViewRepository extends CrudRepository<ProductForecastView, ProductVersionId> {
    public List<ProductForecastView> findByProductVersionId_ProductId(String productId);
    public List<ProductForecastView> findByProductVersionId_ProductId(String productId, Sort sort);
    public List<ProductForecastView> findByProductVersionId(ProductVersionId productVersionId, Sort sort);
    public ProductForecastView findFirstByProductVersionId_ProductIdOrderByProductVersionId_FromDateDesc (String productId);
    public List<ProductForecastView> findByProductVersionId_ProductIdAndProductForecastStatusOrderByProductVersionId_FromDateDesc(String productId, ProductForecastStatus productForecastStatus);

    public List<ProductForecastView> findByProductVersionId_ProductIdAndProductVersionId_FromDateGreaterThan(String productId, LocalDateTime fromDate);

    public List<ProductForecastView> findByProductVersionId_ProductIdAndProductVersionId_FromDateLessThan(String productId, LocalDateTime fromDate);

    public List<ProductForecastView> findByProductVersionId_ProductIdAndProductVersionId_FromDateBetween(String productId, LocalDateTime startDate, LocalDateTime endDate);

    public List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateGreaterThan(String productId, LocalDateTime endDate);

    public List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateLessThan(String productId, LocalDateTime endDate);

    public List<ProductForecastView> findByProductVersionId_ProductIdAndEndDateBetween(String productId, LocalDateTime startDate, LocalDateTime endDate);
}
