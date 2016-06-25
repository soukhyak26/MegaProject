package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import com.affaince.subscription.product.vo.ProductMonthlyVersionId;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 30-04-2016.
 */
public interface ProductForecastMetricsViewRepository extends CrudRepository<ProductForecastMetricsView, com.affaince.subscription.common.vo.ProductMonthlyVersionId> {
    List<ProductForecastMetricsView> findByProductId(String productId);
    public List<ProductForecastMetricsView> findByProductMonthlyVersionId_ProductId(String productId);
    public List<ProductForecastMetricsView> findByProductMonthlyVersionId_ProductId(String productId,Sort sort);
    public List<ProductForecastMetricsView> findByProductMonthlyVersionId(ProductMonthlyVersionId productMonthlyVersionId, Sort sort);

}