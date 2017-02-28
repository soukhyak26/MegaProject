package com.affaince.subscription.metrics.query.repository;

import com.affaince.subscription.metrics.query.view.ProductActivationStatusView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by anayonkar on 13/2/16.
 */
public interface ProductActivationStatusViewRepository extends CrudRepository<ProductActivationStatusView, String> {
    ProductActivationStatusView findByProductId(String productId);
}
