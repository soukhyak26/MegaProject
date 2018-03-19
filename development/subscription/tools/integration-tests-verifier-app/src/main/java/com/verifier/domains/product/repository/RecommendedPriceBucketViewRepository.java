package com.verifier.domains.product.repository;

import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import com.verifier.domains.product.view.RecommendedPriceBucketView;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by mandark on 28-01-2016.
 */
public interface RecommendedPriceBucketViewRepository extends PagingAndSortingRepository<RecommendedPriceBucketView, ProductwisePriceBucketId> {

    @Query("{ entityStatus:'1' }")
    List<RecommendedPriceBucketView> findAll(Sort sort);

    @Query("{ entityStatus:'1' }")
    List<RecommendedPriceBucketView> findByProductwisePriceBucketId_ProductId(String productId);
}
