package com.affaince.subscription.product.registration.query.repository;

import com.affaince.subscription.product.registration.query.view.PriceBucketView;
import com.affaince.subscription.product.registration.query.view.ProductVersionId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by mandark on 28-01-2016.
 */
public interface PriceBucketViewRepository extends PagingAndSortingRepository<PriceBucketView, ProductVersionId> {

    @Query("{ entityStatus:'1' }")
    PriceBucketView findOne(Sort sort);
}
