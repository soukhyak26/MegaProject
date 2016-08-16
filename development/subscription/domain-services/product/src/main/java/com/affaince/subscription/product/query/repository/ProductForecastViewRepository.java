package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductForecastView;
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
}
