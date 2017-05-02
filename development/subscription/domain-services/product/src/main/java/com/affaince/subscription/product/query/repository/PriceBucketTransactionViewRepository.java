package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.product.query.view.PriceBucketTransactionView;
import com.affaince.subscription.product.vo.PriceBucketTransactionId;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mandar on 5/2/2017.
 */
public interface PriceBucketTransactionViewRepository extends CrudRepository<PriceBucketTransactionView,PriceBucketTransactionId> {
}
