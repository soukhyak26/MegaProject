package com.affaince.subscription.pricing.query.repository;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.query.view.ProductVersionId;
import com.affaince.subscription.pricing.vo.PriceBucket;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by mandark on 28-01-2016.
 */
public interface PriceBucketViewRepository extends PagingAndSortingRepository<PriceBucketView, ProductVersionId> {

    @Query("{ entityStatus:'1' }")
    PriceBucketView findOne(Sort sort);
    @Query("{ entityStatus:'1' }")
    List<PriceBucketView> findAll(Sort sort);
    List<PriceBucketView> findByProductVersionIdAndEntityStatus(ProductVersionId versionId,int entityStatus);
}
