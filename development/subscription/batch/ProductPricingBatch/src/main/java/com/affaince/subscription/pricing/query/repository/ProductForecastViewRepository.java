package com.affaince.subscription.pricing.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.pricing.query.view.ProductForecastView;
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

}