package com.affaince.subscription.product.query.repository;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by mandark on 28-01-2016.
 */
public interface PriceBucketViewRepository extends PagingAndSortingRepository<PriceBucketView, ProductwisePriceBucketId> {

    @Query("{ entityStatus:'1' }")
    PriceBucketView findOne(ProductwisePriceBucketId productwisePriceBucketId);
    @Query("{ entityStatus:'1' }")
    List<PriceBucketView> findAll(Sort sort);
    @Query("{ entityStatus:'1' }")
    List<PriceBucketView> findByProductwisePriceBucketId_ProductId(String productId);

    @Query("{ entityStatus:'1' }")
    List<PriceBucketView> findByProductwisePriceBucketId_ProductId(String productId, Sort sort);
    List<PriceBucketView> findByProductwisePriceBucketId_ProductIdAndTaggedPriceVersion_PurchasePricePerUnit(String productId, double purchasePricePerUnit);
    List<PriceBucketView> findByProductwisePriceBucketIdAndEntityStatus(ProductwisePriceBucketId productwisePriceBucketId, int entityStatus);

}
