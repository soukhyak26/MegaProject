package com.affaince.subscription.subscriber.query.repository;

import com.affaince.subscription.subscriber.query.view.ProductView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rsavaliya on 17/1/16.
 */
public interface ProductViewRepository extends CrudRepository<ProductView, String> {
}
