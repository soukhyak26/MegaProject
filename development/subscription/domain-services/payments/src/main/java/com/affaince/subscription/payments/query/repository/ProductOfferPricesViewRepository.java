package com.affaince.subscription.payments.query.repository;

import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import com.affaince.subscription.payments.query.view.ProductOfferPricesView;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 5/26/2017.
 */
public interface ProductOfferPricesViewRepository extends CrudRepository<ProductOfferPricesView,ProductwisePriceBucketId>{
}
