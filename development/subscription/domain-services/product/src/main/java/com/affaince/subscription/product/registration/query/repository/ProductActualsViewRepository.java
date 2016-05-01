package com.affaince.subscription.product.registration.query.repository;

import com.affaince.subscription.product.registration.query.view.ProductActualsView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */
public interface ProductActualsViewRepository extends CrudRepository<ProductActualsView,String> {
    List<ProductActualsView> findByProductId(String productId);
}
