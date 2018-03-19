package com.verifier.domains.subscriber.repository;

import com.verifier.domains.subscriber.view.ProductView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rsavaliya on 17/1/16.
 */
public interface SubscriberProductViewRepository extends CrudRepository<ProductView, String> {
}
