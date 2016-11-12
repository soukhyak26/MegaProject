package com.affaince.subscription.business.query.repository;

import com.affaince.subscription.business.query.view.ProductView;
import com.affaince.subscription.common.type.ProductStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by mandark on 26-01-2016.
 */
public interface ProductViewRepository extends CrudRepository<ProductView, String> {
    ProductView findByProductId(String productId);

    List<ProductView> findByProductStatus(ProductStatus productStatus);
}
