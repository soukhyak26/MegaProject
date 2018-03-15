package com.verifier.domains.product.repository;

import com.affaince.subscription.common.type.ProductStatus;
import com.verifier.domains.product.view.ProductActivationStatusView;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by anayonkar on 13/2/16.
 */
public interface ProductActivationStatusViewRepository extends CrudRepository<ProductActivationStatusView, String>{
    ProductActivationStatusView findByProductId(String productId);
    List<ProductActivationStatusView> findAllByProductStatusesIn (ProductStatus productStatus);
}
