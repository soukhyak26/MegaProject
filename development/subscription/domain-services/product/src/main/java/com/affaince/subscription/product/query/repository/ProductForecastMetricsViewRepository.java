package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 30-04-2016.
 */

public interface ProductForecastMetricsViewRepository extends CrudRepository<ProductForecastMetricsView, com.affaince.subscription.common.vo.ProductVersionId> {
    List<ProductForecastMetricsView> findByProductVersionId_ProductId(String productId);

    List<ProductForecastMetricsView> findByProductVersionId_ProductId(String productId, Sort sort);

    List<ProductForecastMetricsView> findByProductVersionId(ProductVersionId productVersionId, Sort sort);

}
