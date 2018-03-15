package com.verifier.domains.product.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.verifier.domains.product.view.ProductActualMetricsView;
import org.joda.time.LocalDate;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */
public interface ProductActualMetricsViewRepository extends CrudRepository<ProductActualMetricsView, ProductVersionId> {
    //List<ProductActualMetricsView> findByProductId(String productId);
    List<ProductActualMetricsView> findByProductVersionId_ProductId(String productId);
    List<ProductActualMetricsView> findByProductVersionId_ProductId(String productId, Sort sort);
    List<ProductActualMetricsView> findByProductVersionId(ProductVersionId productVersionId);
    List<ProductActualMetricsView> findByProductVersionId_ProductIdAndEndDate(String productId, LocalDate endDate);
}
