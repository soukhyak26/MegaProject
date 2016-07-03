package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.vo.ProductMonthlyVersionId;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */
public interface ProductActualMetricsViewRepository extends CrudRepository<ProductActualMetricsView, com.affaince.subscription.common.vo.ProductVersionId> {
    //List<ProductActualMetricsView> findByProductId(String productId);
    public List<ProductActualMetricsView> findByProductVersionId_ProductId(String productId);
    public List<ProductActualMetricsView> findByProductVersionId_ProductId(String productId,Sort sort);
    public List<ProductActualMetricsView> findByProductVersionId(ProductVersionId productVersionId, Sort sort);

}
