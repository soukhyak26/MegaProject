package com.affaince.subscription.product.registration.query.repository;

import com.affaince.subscription.product.registration.query.view.ProductActivationStatusView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by anayonkar on 13/2/16.
 */
public interface ProductActivationStatusViewRepository extends CrudRepository<ProductActivationStatusView, String>{
    public ProductActivationStatusView findByProductId(String productId);
}
