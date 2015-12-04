package com.affaince.subscription.product.registration.query.repository;

import com.affaince.subscription.product.registration.query.view.ProductView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public interface ProductRepository extends CrudRepository<ProductView, String> {
    ProductView findOneByItemId(String itemId);
}
