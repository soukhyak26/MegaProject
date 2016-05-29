package com.affaince.subscription.product.registration.query.repository;

import com.affaince.subscription.product.registration.query.view.ProductActualMetricsView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */
public interface ProductActualMetricsViewRepository extends CrudRepository<ProductActualMetricsView,String> {
    List<ProductActualMetricsView> findByProductId(String productId);
}
