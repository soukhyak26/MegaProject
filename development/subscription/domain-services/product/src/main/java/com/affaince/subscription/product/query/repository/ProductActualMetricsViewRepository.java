package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.vo.ProductMonthlyVersionId;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */
public interface ProductActualMetricsViewRepository extends CrudRepository<ProductActualMetricsView, com.affaince.subscription.common.vo.ProductMonthlyVersionId> {
    List<ProductActualMetricsView> findByProductId(String productId);
    public List<ProductActualMetricsView> findByProductMonthlyVersionId_ProductId(String productId);
    public List<ProductActualMetricsView> findByProductMonthlyVersionId_ProductId(String productId,Sort sort);
    public List<ProductActualMetricsView> findByProductMonthlyVersionId(ProductMonthlyVersionId productMonthlyVersionId, Sort sort);

}