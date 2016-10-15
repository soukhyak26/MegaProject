package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */
public interface ProductActualMetricsViewRepository extends CrudRepository<ProductActualMetricsView, com.affaince.subscription.common.vo.ProductVersionId> {
    //List<ProductActualMetricsView> findByProductId(String productId);
    List<ProductActualMetricsView> findByProductVersionId_ProductId(String productId);

    List<ProductActualMetricsView> findByProductVersionId_ProductId(String productId, Sort sort);

    List<ProductActualMetricsView> findByProductVersionId(ProductVersionId productVersionId, Sort sort);

}
