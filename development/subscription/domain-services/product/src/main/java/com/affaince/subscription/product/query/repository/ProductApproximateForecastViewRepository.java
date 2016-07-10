package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductApproximateForecastView;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 30-04-2016.
 */

public interface ProductApproximateForecastViewRepository extends CrudRepository<ProductApproximateForecastView, ProductVersionId> {
    public List<ProductApproximateForecastView> findByProductVersionId_ProductId(String productId);
    public List<ProductApproximateForecastView> findByProductVersionId_ProductId(String productId, Sort sort);
    public List<ProductApproximateForecastView> findByProductVersionId(ProductVersionId productVersionId, Sort sort);

}